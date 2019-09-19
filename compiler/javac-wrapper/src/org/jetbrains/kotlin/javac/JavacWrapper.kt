/*
 * Copyright 2010-2017 JetBrains s.r.o.
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

package org.jetbrains.kotlin.javac

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.StandardFileSystems
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.psi.search.EverythingGlobalScope
import com.intellij.psi.search.GlobalSearchScope
import com.sun.source.tree.CompilationUnitTree
import com.sun.tools.javac.code.Flags
import com.sun.tools.javac.code.Symbol
import com.sun.tools.javac.code.Symtab
import com.sun.tools.javac.file.JavacFileManager
import com.sun.tools.javac.jvm.ClassReader
import com.sun.tools.javac.main.JavaCompiler
import com.sun.tools.javac.model.JavacElements
import com.sun.tools.javac.model.JavacTypes
import com.sun.tools.javac.tree.JCTree
import com.sun.tools.javac.util.Context
import com.sun.tools.javac.util.Log
import com.sun.tools.javac.util.Names
import com.sun.tools.javac.util.Options
import org.jetbrains.kotlin.cli.jvm.compiler.JvmPackagePartProvider
import org.jetbrains.kotlin.javac.resolve.ClassifierResolver
import org.jetbrains.kotlin.javac.resolve.IdentifierResolver
import org.jetbrains.kotlin.javac.resolve.KotlinClassifiersCache
import org.jetbrains.kotlin.javac.resolve.classId
import org.jetbrains.kotlin.javac.wrappers.symbols.*
import org.jetbrains.kotlin.javac.wrappers.trees.*
import org.jetbrains.kotlin.load.java.structure.*
import org.jetbrains.kotlin.name.*
import org.jetbrains.kotlin.psi.KtFile
import java.io.BufferedWriter
import java.io.Closeable
import java.io.File
import javax.lang.model.element.Element
import javax.lang.model.type.TypeMirror
import javax.tools.JavaFileManager
import javax.tools.JavaFileObject
import javax.tools.StandardLocation.*
import com.sun.tools.javac.util.List as JavacList

class JavacWrapper(
    javaFiles: Collection<File>,
    kotlinFiles: Collection<KtFile>,
    arguments: Array<String>?,
    jvmClasspathRoots: List<File>,
    jvmModularRoots: List<File>,
    bootClasspath: List<File>?,
    sourcePath: List<File>?,
    val kotlinResolver: JavacWrapperKotlinResolver,
    private val packagePartsProviders: List<JvmPackagePartProvider>,
    private val compileJava: Boolean,
    private val outputDirectory: File?,
    private val context: Context
) : Closeable {
    private val localFileSystem = VirtualFileManager.getInstance().getFileSystem(StandardFileSystems.FILE_PROTOCOL)!!
    private val jarFileSystem = VirtualFileManager.getInstance().getFileSystem(StandardFileSystems.JAR_PROTOCOL)!!
    val LOG = File("C:/KK/wrapper.log").bufferedWriter()

    init {
        LOG.info("JavacWrapper created")
    }

    private fun BufferedWriter.info(s: String) {
        write(s)
        newLine()
        flush()
    }

    companion object {
        fun getInstance(project: Project): JavacWrapper = ServiceManager.getService(project, JavacWrapper::class.java)
    }

    private fun createCommonClassifierType(classId: ClassId) =
        findClassInSymbols(classId)?.let {
            SymbolBasedClassifierType(it.element.asType(), this)
        }

    val JAVA_LANG_OBJECT by lazy {
        createCommonClassifierType(classId("java.lang", "Object"))
    }

    val JAVA_LANG_ENUM by lazy {
        findClassInSymbols(classId("java.lang", "Enum"))
    }

    val JAVA_LANG_ANNOTATION_ANNOTATION by lazy {
        createCommonClassifierType(classId("java.lang.annotation", "Annotation"))
    }

    init {
        Options.instance(context).let { options ->
            JavacOptionsMapper.setUTF8Encoding(options)
            arguments?.toList()?.let { JavacOptionsMapper.map(options, it) }
        }
    }

    private val javac = object : JavaCompiler(context) {
        override fun parseFiles(files: Iterable<JavaFileObject>?) = compilationUnits
    }

    private val fileManager = context[JavaFileManager::class.java] as JavacFileManager

    init {
        // keep javadoc comments
        javac.keepComments = true
        // use rt.jar instead of lib/ct.sym
        fileManager.setSymbolFileEnabled(false)
        bootClasspath?.let {
            val cp = fileManager.getLocation(PLATFORM_CLASS_PATH) + jvmClasspathRoots
            fileManager.setLocation(PLATFORM_CLASS_PATH, it)
            LOG.info("Platform class path is $it")
            fileManager.setLocation(CLASS_PATH, cp)
            LOG.info("Class path is $cp")
        } ?: run {
            fileManager.setLocation(CLASS_PATH, jvmClasspathRoots)
            LOG.info("Modular class path is $jvmModularRoots")
            LOG.info("Class path (no boot) is $jvmClasspathRoots")
            LOG.info("Platform class path (no boot) is ${fileManager.getLocation(PLATFORM_CLASS_PATH)}")
        }
        sourcePath?.let {
            fileManager.setLocation(SOURCE_PATH, sourcePath)
            LOG.info("Source path is $sourcePath")
        }
    }

    private val names = Names.instance(context)
    private val symTab = Symtab.instance(context)
    private val elements = JavacElements.instance(context)
    private val types = JavacTypes.instance(context)
    private val fileObjects = fileManager.getJavaFileObjectsFromFiles(javaFiles).toJavacList()
    private val compilationUnits: JavacList<JCTree.JCCompilationUnit> = fileObjects.map(javac::parse).toJavacList()

    private val treeBasedJavaClasses = compilationUnits.flatMap { unit ->
        unit.typeDecls.map { classDeclaration ->
            val packageName = unit.packageName?.toString() ?: ""
            val className = (classDeclaration as JCTree.JCClassDecl).simpleName.toString()
            val classId = classId(packageName, className)
            classId to TreeBasedClass(classDeclaration, unit, this, classId, null)
        }
    }.toMap()

    private val treeBasedJavaPackages = compilationUnits
        .mapTo(hashSetOf<TreeBasedPackage>()) { unit ->
            unit.packageName?.toString()?.let { packageName ->
                TreeBasedPackage(packageName, this, unit)
            } ?: TreeBasedPackage("<root>", this, unit)
        }
        .associateBy(TreeBasedPackage::fqName)

    private val packageSourceAnnotations = compilationUnits
        .filter {
            it.sourceFile.isNameCompatible("package-info", JavaFileObject.Kind.SOURCE) &&
                    it.packageName != null
        }.associateBy({ FqName(it.packageName!!.toString()) }) { compilationUnit ->
            compilationUnit.packageAnnotations
        }

    private val classifierResolver = ClassifierResolver(this)
    private val identifierResolver = IdentifierResolver(this)
    private val kotlinClassifiersCache by lazy { KotlinClassifiersCache(if (javaFiles.isNotEmpty()) kotlinFiles else emptyList(), this) }
    private val symbolBasedPackagesCache = hashMapOf<String, SymbolBasedPackage?>()
    private val symbolBasedClassesCache = hashMapOf<ClassId, SymbolBasedClass>()

    fun compile(outDir: File? = null): Boolean = with(javac) {
        if (!compileJava) return true
        LOG.info("Java compilation via JavacWrapper started")
        var errorCount = errorCount()
        LOG.info("Java compilation via JavacWrapper: errorCount() = $errorCount")
        if (errorCount > 0) return false
        LOG.info("Java compilation via JavacWrapper: Step 1")

        val javaFilesNumber = fileObjects.length()
        if (javaFilesNumber == 0) return true

        fileManager.setClassPathForCompilation(outDir)
        LOG.info("Java compilation via JavacWrapper: Step 2")
        val information = "Compiling $javaFilesNumber Java source files" +
                " to [${fileManager.getLocation(CLASS_OUTPUT)?.firstOrNull()?.path}]"
        LOG.info(information)
        context.get(Log.outKey)?.println(information)
        compile(fileObjects)
        LOG.info("Java compilation via JavacWrapper: Step 3")
        errorCount = errorCount()
        LOG.info("Java compilation via JavacWrapper: errorCount() = $errorCount")
        errorCount == 0
    }

    override fun close() {
        fileManager.close()
        javac.close()
        LOG.close()
    }

    fun findClass(classId: ClassId, scope: GlobalSearchScope = EverythingGlobalScope()): JavaClass? {
        LOG.info("JavacWrapper findClass $classId")
        if (classId.isNestedClass) {
            val pathSegments = classId.relativeClassName.pathSegments().map { it.asString() }
            val outerClassId = ClassId(classId.packageFqName, Name.identifier(pathSegments.first()))
            var outerClass = findClass(outerClassId, scope) ?: return null

            pathSegments.drop(1).forEach {
                outerClass = outerClass.findInnerClass(Name.identifier(it)) ?: return null
            }

            return outerClass
        }

        treeBasedJavaClasses[classId]?.let { javaClass ->
            javaClass.virtualFile?.let { if (it in scope) return javaClass }
        }

        LOG.info("JavacWrapper findClass $classId: not found in tree-based storage")

        if (symbolBasedClassesCache.containsKey(classId)) {
            val javaClass = symbolBasedClassesCache[classId]
            javaClass?.virtualFile?.let { file ->
                if (file in scope) return javaClass
            }
        }

        LOG.info("JavacWrapper findClass $classId: not found in symbol-based cache")

        findPackageInSymbols(classId.packageFqName.asString())?.let {
            (it.element as Symbol.PackageSymbol).findClass(classId)?.let { javaClass ->
                javaClass.virtualFile?.let { file ->
                    if (file in scope) return javaClass
                }
            }

        }

        LOG.info("JavacWrapper findClass $classId: not found at all")
        return null
    }

    fun findPackage(fqName: FqName, scope: GlobalSearchScope = EverythingGlobalScope()): JavaPackage? {
        LOG.info("JavacWrapper findPackage $fqName")
        treeBasedJavaPackages[fqName]?.let { javaPackage ->
            javaPackage.virtualFile?.let { file ->
                if (file in scope) return javaPackage
            }
        }

        return findPackageInSymbols(fqName.asString())
    }

    fun findSubPackages(fqName: FqName): List<JavaPackage> {
        LOG.info("JavacWrapper findSubPackages $fqName")
        return symTab.packages
            .filterKeys { it.toString().startsWith("$fqName.") }
            .map { SimpleSymbolBasedPackage(it.value, this) } +
                treeBasedJavaPackages
                    .filterKeys { it.isSubpackageOf(fqName) && it != fqName }
                    .map { it.value }
    }

    fun getPackageAnnotationsFromSources(fqName: FqName): List<JCTree.JCAnnotation> =
        packageSourceAnnotations[fqName] ?: emptyList()

    fun findClassesFromPackage(fqName: FqName): List<JavaClass> =
        treeBasedJavaClasses
            .filterKeys { it.packageFqName == fqName }
            .map { treeBasedJavaClasses[it.key]!! } +
                elements.getPackageElement(fqName.asString())
                    ?.members()
                    ?.elements
                    ?.filterIsInstance(Symbol.ClassSymbol::class.java)
                    ?.map { SymbolBasedClass(it, this, null, it.classfile) }
                    .orEmpty()

    fun knownClassNamesInPackage(fqName: FqName): Set<String> {
        LOG.info("JavacWrapper knownClassNamesInPackage $fqName")
        return treeBasedJavaClasses
            .filterKeys { it.packageFqName == fqName }
            .mapTo(hashSetOf()) { it.value.name.asString() } +
                elements.getPackageElement(fqName.asString())
                    ?.members_field
                    ?.elements
                    ?.filterIsInstance<Symbol.ClassSymbol>()
                    ?.map { it.name.toString() }
                    .orEmpty()
    }

    fun getKotlinClassifier(classId: ClassId): JavaClass? =
        kotlinClassifiersCache.getKotlinClassifier(classId)

    fun isDeprecated(element: Element) = elements.isDeprecated(element)

    fun isDeprecated(typeMirror: TypeMirror) = isDeprecated(types.asElement(typeMirror))

    fun resolve(tree: JCTree, compilationUnit: CompilationUnitTree, containingElement: JavaElement): JavaClassifier? =
        classifierResolver.resolve(tree, compilationUnit, containingElement)

    fun resolveField(tree: JCTree, compilationUnit: CompilationUnitTree, containingClass: JavaClass): JavaField? =
        identifierResolver.resolve(tree, compilationUnit, containingClass)

    fun toVirtualFile(javaFileObject: JavaFileObject): VirtualFile? =
        javaFileObject.toUri().let { uri ->
            if (uri.scheme == "jar") {
                jarFileSystem.findFileByPath(uri.schemeSpecificPart.substring("file:".length))
            } else {
                localFileSystem.findFileByPath(uri.schemeSpecificPart)
            }
        }

    fun hasKotlinPackage(fqName: FqName) =
        if (kotlinClassifiersCache.hasPackage(fqName)) {
            fqName
        } else {
            null
        }

    fun isDeprecatedInJavaDoc(tree: JCTree, compilationUnit: CompilationUnitTree) =
        (compilationUnit as JCTree.JCCompilationUnit).docComments?.getCommentTree(tree)?.comment?.isDeprecated == true

    private inline fun <reified T> Iterable<T>.toJavacList() = JavacList.from(this)

    private fun findClassInSymbols(classId: ClassId): SymbolBasedClass? =
        elements.getTypeElement(classId.asSingleFqName().asString())?.let { symbol ->
            SymbolBasedClass(symbol, this, classId, symbol.classfile)
        }

    private fun findPackageInSymbols(fqName: String): SymbolBasedPackage? {
        if (symbolBasedPackagesCache.containsKey(fqName)) return symbolBasedPackagesCache[fqName]

        fun findSimplePackageInSymbols(fqName: String): SimpleSymbolBasedPackage? {
            elements.getPackageElement(fqName)?.let { symbol ->
                SimpleSymbolBasedPackage(symbol, this)
            }.let { symbolBasedPackage ->
                symbolBasedPackagesCache[fqName] = symbolBasedPackage
                return symbolBasedPackage
            }
        }

        val mappedPackages = mutableListOf<SimpleSymbolBasedPackage>()
        for (provider in packagePartsProviders) {
            val packagePartFqNames = provider.findPackageParts(fqName)
                .map { it.substringBeforeLast("/").replace('/', '.') }.filter { it != fqName }.distinct()
            mappedPackages += packagePartFqNames.mapNotNull { packagePartFqName ->
                findSimplePackageInSymbols(packagePartFqName)
            }
        }
        if (mappedPackages.isNotEmpty()) {
            val symbolBasedPackage = MappedSymbolBasedPackage(FqName(fqName), mappedPackages, this)
            symbolBasedPackagesCache[fqName] = symbolBasedPackage
            return symbolBasedPackage
        }

        return findSimplePackageInSymbols(fqName)
    }

    private fun JavacFileManager.setClassPathForCompilation(outDir: File?) = apply {
        (outDir ?: outputDirectory)?.let { outputDir ->
            if (outputDir.exists()) {
                fileManager.setLocation(CLASS_PATH, fileManager.getLocation(CLASS_PATH) + outputDir)
            }
            outputDir.mkdirs()
            fileManager.setLocation(CLASS_OUTPUT, listOf(outputDir))

        }

        LOG.info("CLASS_OUTPUT: " + fileManager.getLocation(CLASS_OUTPUT))
        LOG.info("CLASS_PATH: " + fileManager.getLocation(CLASS_PATH))

        val reader = ClassReader.instance(context)
        val names = Names.instance(context)
        val outDirName = getLocation(CLASS_OUTPUT)?.firstOrNull()?.path ?: ""

        list(CLASS_OUTPUT, "", setOf(JavaFileObject.Kind.CLASS), true)
            .forEach { fileObject ->
                LOG.info("JavacWrapper analyses ${fileObject.name}")
                val fqName = fileObject.name
                    .substringAfter(outDirName)
                    .substringBefore(".class")
                    .replace(File.separator, ".")
                    .let { className ->
                        if (className.startsWith(".")) className.substring(1) else className
                    }.let(names::fromString)

                symTab.classes[fqName]?.let { it ->
                    LOG.info("JavacWrapper clears $fqName.class ClassSymbol: $it")
                    symTab.classes[fqName] = null
                } ?: LOG.info("JavacWrapper did not find $fqName.class ClassSymbol")
                val symbol = reader.enterClass(fqName, fileObject)

                (elements.getPackageOf(symbol) as? Symbol.PackageSymbol)?.let { packageSymbol ->
                    LOG.info("JavacWrapper set ${packageSymbol.fullname} to 'exists'")
                    packageSymbol.members_field?.enter(symbol)
                    packageSymbol.flags_field = packageSymbol.flags_field or Flags.EXISTS.toLong()
                }
            }

    }

    private fun Symbol.PackageSymbol.findClass(classId: ClassId): SymbolBasedClass? {
        LOG.info("JavacWrapper findClass $classId: in package $fullname")
        val name = classId.relativeClassName.asString()
        val nameParts = name.replace("$", ".").split(".")
        LOG.info("JavacWrapper findClass $classId: short $name split into $nameParts")
        LOG.info("JavacWrapper findClass: package $fullname content: " + members_field?.elements?.toList())
        val alternativeSymbol = symTab.packages[fullname]
        if (alternativeSymbol != null) {
            LOG.info(
                "JavacWrapper findClass: alternative package $fullname content: " +
                        alternativeSymbol.members_field?.elements?.toList()
            )
        }
        var symbol = members_field?.getElementsByName(names.fromString(nameParts.first()))
            ?.firstOrNull() as? Symbol.ClassSymbol ?: return null
        if (nameParts.size > 1) {
            symbol.complete()
            for (it in nameParts.drop(1)) {
                symbol = symbol.members_field?.getElementsByName(names.fromString(it))?.firstOrNull() as? Symbol.ClassSymbol ?: return null
                symbol.complete()
            }
        }

        return symbol.let { SymbolBasedClass(it, this@JavacWrapper, classId, it.classfile) }
            .apply { symbolBasedClassesCache[classId] = this }
    }

}