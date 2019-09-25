/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.caches.resolve

import com.google.common.collect.ImmutableMap
import com.intellij.openapi.progress.ProcessCanceledException
import com.intellij.openapi.project.DumbService
import com.intellij.openapi.project.IndexNotReadyException
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Getter
import com.intellij.openapi.util.ModificationTracker
import com.intellij.psi.PsiElement
import com.intellij.psi.util.*
import org.jetbrains.kotlin.analyzer.AnalysisResult
import org.jetbrains.kotlin.container.ComponentProvider
import org.jetbrains.kotlin.container.get
import org.jetbrains.kotlin.context.GlobalContext
import org.jetbrains.kotlin.context.withModule
import org.jetbrains.kotlin.context.withProject
import org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithSource
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.diagnostics.Diagnostic
import org.jetbrains.kotlin.diagnostics.DiagnosticUtils
import org.jetbrains.kotlin.frontend.di.createContainerForLazyBodyResolve
import org.jetbrains.kotlin.idea.caches.project.getModuleInfo
import org.jetbrains.kotlin.idea.caches.trackers.cleanInBlockModificationCount
import org.jetbrains.kotlin.idea.caches.trackers.inBlockModificationCount
import org.jetbrains.kotlin.idea.project.IdeaModuleStructureOracle
import org.jetbrains.kotlin.idea.project.TargetPlatformDetector
import org.jetbrains.kotlin.idea.project.findAnalyzerServices
import org.jetbrains.kotlin.idea.project.languageVersionSettings
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.parentsWithSelf
import org.jetbrains.kotlin.resolve.*
import org.jetbrains.kotlin.resolve.diagnostics.Diagnostics
import org.jetbrains.kotlin.resolve.diagnostics.SimpleDiagnostics
import org.jetbrains.kotlin.resolve.lazy.BodyResolveMode
import org.jetbrains.kotlin.resolve.lazy.ResolveSession
import org.jetbrains.kotlin.resolve.source.getPsi
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.util.slicedMap.ReadOnlySlice
import org.jetbrains.kotlin.util.slicedMap.WritableSlice
import java.util.*

internal class PerFileAnalysisCache(val file: KtFile, componentProvider: ComponentProvider) {
    private val globalContext = componentProvider.get<GlobalContext>()
    private val moduleDescriptor = componentProvider.get<ModuleDescriptor>()
    private val resolveSession = componentProvider.get<ResolveSession>()
    private val codeFragmentAnalyzer = componentProvider.get<CodeFragmentAnalyzer>()
    private val bodyResolveCache = componentProvider.get<BodyResolveCache>()

    private val cache = HashMap<PsiElement, CachedValue<AnalysisResult>>()

    private fun lookUp(analyzableElement: KtElement): CachedValue<AnalysisResult>? {
        // Looking for parent elements that are already analyzed
        // Also removing all elements whose parents are already analyzed, to guarantee consistency
        val descendantsOfCurrent = arrayListOf<PsiElement>()
        val toRemove = hashSetOf<PsiElement>()

        var result: CachedValue<AnalysisResult>? = null
        for (current in analyzableElement.parentsWithSelf) {
            val cached = cache[current]
            if (cached != null) {
                result = cached
                toRemove.addAll(descendantsOfCurrent)
                descendantsOfCurrent.clear()
            }

            if (isModified(current)) break

            descendantsOfCurrent.add(current)
        }

        cache.keys.removeAll(toRemove)

        return result
    }

    private fun isModified(current: PsiElement): Boolean {
        // Modified KtNamedFunction provides its own AnalysisResult
        // TODO: could be generalized as well for other cases those could provide incremental analysis

        return current is KtNamedFunction && current.inBlockModificationCount > 0
    }

    fun getAnalysisResults(element: KtElement): AnalysisResult {
        assert(element.containingKtFile == file) { "Wrong file. Expected $file, but was ${element.containingKtFile}" }

        val analyzableParent = KotlinResolveDataProvider.findAnalyzableParent(element)

        // cache does not contain AnalysisResult per each kt/psi element
        // instead it looks up analysis for its parents - see lookUp(analyzableElement)

        // top element (ktFile) always contains analysis for entire file
        // cache contains analysis of one of parents if
        // that parent (e.g. ktNamedFunction) is modified and could provide incremental analysis
        // - in this case top element has details about what item performed incremental analysis (its parent ctx, parent diagnostics etc)

        return synchronized<AnalysisResult>(this) {
            val cached = lookUp(analyzableParent)
            if (cached != null) return@synchronized cached.value

            val result =
                // TODO: could be generalized as well for other cases those could provide incremental analysis
                if (analyzableParent is KtNamedFunction) {
                    CachedValuesManager.getManager(file.project).createCachedValue(
                        {
                            val parentCache = cache[file] as? SimpleCachedValue
                            val calculatedAnalysisResult: AnalysisResult =
                                if (parentCache != null && analyzableParent.inBlockModificationCount > 0L) {
                                    val result = analyze(analyzableParent)

                                    val parentAnalysis = parentCache.value
                                    val newBindingCtx = mergeContexts(result, parentAnalysis, analyzableParent)

                                    val newParentAnalysis = if (parentAnalysis.isError())
                                        AnalysisResult.internalError(newBindingCtx, parentAnalysis.error)
                                    else AnalysisResult.success(
                                        newBindingCtx,
                                        parentAnalysis.moduleDescriptor,
                                        parentAnalysis.shouldGenerateCode
                                    )
                                    cache[file] = SimpleCachedValue(newParentAnalysis)

                                    newParentAnalysis
                                } else {
                                    parentCache?.value ?: analyze(analyzableParent)
                                }

                            CachedValueProvider.Result.create(calculatedAnalysisResult,
                                                              ModificationTracker { analyzableParent.inBlockModificationCount })
                        }, false
                    )
                } else {
                    SimpleCachedValue(analyze(analyzableParent))
                }
            cache[analyzableParent] = result

            return@synchronized result.value
        }
    }

    private fun findAllChildren(element: KtElement): Set<PsiElement> {
        val set = mutableSetOf<PsiElement>()
        val visitor = object : KtTreeVisitor<Unit>() {
            override fun visitKtElement(ktElement: KtElement, data: Unit): Void? {
                set.add(ktElement)
                return super.visitKtElement(ktElement, Unit)
            }
        }
        element.acceptChildren(visitor, Unit)
        return set.toSet()
    }

    private fun mergeContexts(
        analysisResult: AnalysisResult,
        parentAnalysis: AnalysisResult,
        element: KtElement
    ): BindingContext {
        val parentCtx = parentAnalysis.bindingContext
        val thisCtx = analysisResult.bindingContext

        val thisDiagnosticsAll = thisCtx.diagnostics.all()

        val sameElementParentCtx: StackedCompositeBindingContext? =
            if (parentCtx is StackedCompositeBindingContext) {
                if (parentCtx.element == element) parentCtx else {
                    // clean up cached analysis result for prev element as it is incorporated into this one
                    cache.remove(parentCtx.element)
                    // have to clean up modification traces
                    if (parentCtx.element is KtNamedFunction) parentCtx.element.cleanInBlockModificationCount()

                    null
                }
            } else null

        // parentDiagnostics contains only diagnostic outside of this element
        // no reason to re-evaluate parentDiagnosticsAll if it is the same element
        val parentDiagnostics: List<Diagnostic> = sameElementParentCtx?.parentDiagnostics ?: run {
            // parentCtx diagnostics can have potentially outdated psi elements
            // so far - traverse from children to parent (expensive) to identify that it is out of this element diagnostic
            val parentDiagnosticsAll = parentCtx.diagnostics.all()
            parentDiagnosticsAll.filter { d ->
                // do not copy diagnostic that is built on a top level as it could be outdated
                for (diagnosticParent in generateSequence(d.psiElement) { it.parent }) {
                    if (diagnosticParent == element) {
                        break
                    }
                    if (diagnosticParent is KtFile) return@filter true
                }
                return@filter false
            }.toList()
        }

        val diagnostics = parentDiagnostics + thisDiagnosticsAll // copy all diagnostics those belongs to `element`

        // how long it could be a list of delegates ? is it worth to perform full analysis when we got too deep ?
        val depth = when (parentCtx) {
            is StackedCompositeBindingContext -> if (parentCtx.element == element) parentCtx.depth else (parentCtx.depth + 1)
            else -> 1
        }

        val ctx = sameElementParentCtx?.parentContext ?: parentCtx

        return StackedCompositeBindingContext(
            depth,
            element, findAllChildren(element),
            thisCtx, ctx,
            parentDiagnostics, SimpleDiagnostics(diagnostics)
        )
    }

    private fun analyze(analyzableElement: KtElement): AnalysisResult {
        val project = analyzableElement.project
        if (DumbService.isDumb(project)) {
            return AnalysisResult.EMPTY
        }

        try {
            return KotlinResolveDataProvider.analyze(
                project,
                globalContext,
                moduleDescriptor,
                resolveSession,
                codeFragmentAnalyzer,
                bodyResolveCache,
                analyzableElement
            )
        } catch (e: ProcessCanceledException) {
            throw e
        } catch (e: IndexNotReadyException) {
            throw e
        } catch (e: Throwable) {
            DiagnosticUtils.throwIfRunningOnServer(e)
            LOG.error(e)

            return AnalysisResult.internalError(BindingContext.EMPTY, e)
        }
    }
}

private class SimpleCachedValue<T>(private val value: T) : CachedValue<T> {
    override fun getValue(): T = value

    override fun getUpToDateOrNull(): Getter<T> = throw UnsupportedOperationException()

    override fun hasUpToDateValue(): Boolean = throw UnsupportedOperationException()

    override fun getValueProvider(): CachedValueProvider<T> = throw UnsupportedOperationException()
}

private class StackedCompositeBindingContext(
    val depth: Int, // depth of stack over original ktFile bindingContext
    val element: KtElement,
    val children: Set<PsiElement>,
    val elementContext: BindingContext,
    val parentContext: BindingContext,
    val parentDiagnostics: List<Diagnostic>,
    private val diagnostics: Diagnostics
) : BindingContext {

    val delegates = listOf(elementContext, parentContext)

    private fun ctx(ktElement: PsiElement?): BindingContext? =
        when {
            ktElement != null -> if (children.contains(ktElement)) elementContext else parentContext
            else -> null
        }

    override fun getType(expression: KtExpression): KotlinType? {
        return ctx(expression)!!.getType(expression)
    }

    override fun <K, V> get(slice: ReadOnlySlice<K, V>?, key: K?): V? {
        val element: PsiElement? = when (key) {
            is PsiElement -> key
            is Call -> key.callElement
            is DeclarationDescriptorWithSource -> key.source.getPsi()
            else -> null
        }

        if (element != null) {
            return ctx(element)!![slice, key]
        }
        val value = delegates.asSequence().map { it[slice, key] }.firstOrNull { it != null }
        return value
    }

    override fun <K, V> getKeys(slice: WritableSlice<K, V>?): Collection<K> {
        return delegates.flatMap { it.getKeys(slice) }
    }

    override fun <K, V> getSliceContents(slice: ReadOnlySlice<K, V>): ImmutableMap<K, V> {
        //we need intermediate map cause ImmutableMap doesn't support same entries obtained from different slices
        val map = hashMapOf<K, V>()
        delegates.forEach { map.putAll(it.getSliceContents(slice)) }
        return ImmutableMap.builder<K, V>().putAll(map).build()
    }

    override fun getDiagnostics(): Diagnostics {
        return diagnostics
    }

    override fun addOwnDataTo(trace: BindingTrace, commitDiagnostics: Boolean) = throw UnsupportedOperationException()
}

private object KotlinResolveDataProvider {
    private val topmostElementTypes = arrayOf<Class<out PsiElement?>?>(
        KtNamedFunction::class.java,
        KtAnonymousInitializer::class.java,
        KtProperty::class.java,
        KtImportDirective::class.java,
        KtPackageDirective::class.java,
        KtCodeFragment::class.java,
        // TODO: Non-analyzable so far, add more granular analysis
        KtAnnotationEntry::class.java,
        KtTypeConstraint::class.java,
        KtSuperTypeList::class.java,
        KtTypeParameter::class.java,
        KtParameter::class.java,
        KtTypeAlias::class.java
    )

    fun findAnalyzableParent(element: KtElement): KtElement {
        if (element is KtFile) return element

        val topmostElement = KtPsiUtil.getTopmostParentOfTypes(element, *topmostElementTypes) as KtElement?
        // parameters and supertype lists are not analyzable by themselves, but if we don't count them as topmost, we'll stop inside, say,
        // object expressions inside arguments of super constructors of classes (note that classes themselves are not topmost elements)
        val analyzableElement = when (topmostElement) {
            is KtAnnotationEntry,
            is KtTypeConstraint,
            is KtSuperTypeList,
            is KtTypeParameter,
            is KtParameter -> PsiTreeUtil.getParentOfType(topmostElement, KtClassOrObject::class.java, KtCallableDeclaration::class.java)
            else -> topmostElement
        }
        // Primary constructor should never be returned
        if (analyzableElement is KtPrimaryConstructor) return analyzableElement.getContainingClassOrObject()
        // Class initializer should be replaced by containing class to provide full analysis
        if (analyzableElement is KtClassInitializer) return analyzableElement.containingDeclaration
        return analyzableElement
        // if none of the above worked, take the outermost declaration
                ?: PsiTreeUtil.getTopmostParentOfType(element, KtDeclaration::class.java)
                // if even that didn't work, take the whole file
                ?: element.containingKtFile
    }

    fun analyze(
        project: Project,
        globalContext: GlobalContext,
        moduleDescriptor: ModuleDescriptor,
        resolveSession: ResolveSession,
        codeFragmentAnalyzer: CodeFragmentAnalyzer,
        bodyResolveCache: BodyResolveCache,
        analyzableElement: KtElement
    ): AnalysisResult {
        try {
            if (analyzableElement is KtCodeFragment) {
                val bodyResolveMode = BodyResolveMode.PARTIAL_FOR_COMPLETION
                val bindingContext = codeFragmentAnalyzer.analyzeCodeFragment(analyzableElement, bodyResolveMode).bindingContext
                return AnalysisResult.success(bindingContext, moduleDescriptor)
            }

            /*
            Note that currently we *have* to re-create LazyTopDownAnalyzer with custom trace in order to disallow resolution of
            bodies in top-level trace (trace from DI-container).
            Resolving bodies in top-level trace may lead to memory leaks and incorrect resolution, because top-level
            trace isn't invalidated on in-block modifications (while body resolution surely does)

            Also note that for function bodies, we'll create DelegatingBindingTrace in ResolveElementCache anyways
            (see 'functionAdditionalResolve'). However, this trace is still needed, because we have other
            codepaths for other KtDeclarationWithBodies (like property accessors/secondary constructors/class initializers)
             */
            val trace = DelegatingBindingTrace(
                resolveSession.bindingContext,
                "Trace for resolution of " + analyzableElement,
                allowSliceRewrite = true
            )

            val moduleInfo = analyzableElement.containingKtFile.getModuleInfo()

            // TODO: should return proper platform!
            val targetPlatform = moduleInfo.platform ?: TargetPlatformDetector.getPlatform(analyzableElement.containingKtFile)

            val lazyTopDownAnalyzer = createContainerForLazyBodyResolve(
                //TODO: should get ModuleContext
                globalContext.withProject(project).withModule(moduleDescriptor),
                resolveSession,
                trace,
                targetPlatform,
                bodyResolveCache,
                targetPlatform.findAnalyzerServices,
                analyzableElement.languageVersionSettings,
                IdeaModuleStructureOracle()
            ).get<LazyTopDownAnalyzer>()

            lazyTopDownAnalyzer.analyzeDeclarations(TopDownAnalysisMode.TopLevelDeclarations, listOf(analyzableElement))

            return AnalysisResult.success(trace.bindingContext, moduleDescriptor)
        } catch (e: ProcessCanceledException) {
            throw e
        } catch (e: IndexNotReadyException) {
            throw e
        } catch (e: Throwable) {
            DiagnosticUtils.throwIfRunningOnServer(e)
            LOG.error(e)

            return AnalysisResult.internalError(BindingContext.EMPTY, e)
        }
    }
}
