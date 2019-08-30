package org.jetbrains.kotlin.tools.dukat.test

/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.util.text.StringUtil
import com.intellij.testFramework.TestDataPath
import com.intellij.util.containers.ContainerUtil
import org.jetbrains.kotlin.checkers.AbstractDiagnosticsTest
import org.jetbrains.kotlin.checkers.DiagnosedRange
import org.jetbrains.kotlin.checkers.TestCheckerUtil
import org.jetbrains.kotlin.checkers.diagnostics.ActualDiagnostic
import org.jetbrains.kotlin.cli.jvm.compiler.EnvironmentConfigFiles
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.config.LanguageVersion
import org.jetbrains.kotlin.descriptors.PackageViewDescriptor
import org.jetbrains.kotlin.descriptors.impl.DeclarationDescriptorVisitorEmptyBodies
import org.jetbrains.kotlin.idea.highlighter.AbstractDiagnosticMessageJsTest
import org.jetbrains.kotlin.idea.highlighter.AbstractDiagnosticMessageTest
import org.jetbrains.kotlin.resolve.lazy.createResolveSessionForFiles
import org.jetbrains.kotlin.test.*
import org.jetbrains.kotlin.test.util.RecursiveDescriptorComparator
import org.jetbrains.kotlin.test.util.RecursiveDescriptorComparator.RECURSIVE
import org.jetbrains.kotlin.test.util.RecursiveDescriptorComparator.RECURSIVE_ALL
import org.jetbrains.kotlin.test.util.RecursiveDescriptorProcessor
import org.junit.Test
import java.io.File
import java.util.regex.Pattern

open class DukatCompareTest : AbstractDiagnosticMessageJsTest() {

    private val oldDataPath = "libraries/tools/dukat/src/test/data/old"
    private val newDataPath = "libraries/stdlib/js/src/org.w3c"

    private fun generatePackageDescriptor(filePath: String, fileName: String): PackageViewDescriptor {
        val psiFile = KotlinTestUtils.createFile(fileName, KotlinTestUtils.doLoadFile(filePath, fileName), project)
        val moduleDescriptor = analyze(psiFile, null, mapOf()).moduleDescriptor
        return moduleDescriptor.getPackage(psiFile.packageFqName)
    }

    override fun doTest(fileName: String) {
        RecursiveDescriptorComparator.compareDescriptors(
            generatePackageDescriptor(oldDataPath, fileName),
            generatePackageDescriptor(newDataPath, fileName),
            RECURSIVE_ALL,
            null
        )
    }

    fun testWebgl() {
        doTest("org.khronos.webgl.kt")
    }

    fun testMasking() {
        doTest("org.w3c.css.masking.kt")
    }

    fun testClipboard() {
        doTest("org.w3c.dom.clipboard.kt")
    }

    fun testCSS() {
        doTest("org.w3c.dom.css.kt")
    }

    fun testEvents() {
        doTest("org.w3c.dom.events.kt")
    }

    fun testDOM() {
        doTest("org.w3c.dom.kt")
    }

    fun testMediacapture() {
        doTest("org.w3c.dom.mediacapture.kt")
    }

    fun testParsing() {
        doTest("org.w3c.dom.parsing.kt")
    }

    fun testPointerEvents() {
        doTest("org.w3c.dom.pointerevents.kt")
    }

    fun testSVG() {
        doTest("org.w3c.dom.svg.kt")
    }

    fun testURL() {
        doTest("org.w3c.dom.url.kt")
    }

    fun testFetch() {
        doTest("org.w3c.fetch.kt")
    }

    fun testFiles() {
        doTest("org.w3c.files.kt")
    }

    fun testNotifications() {
        doTest("org.w3c.notifications.kt")
    }

    fun testPerformance() {
        doTest("org.w3c.performance.kt")
    }

    fun testWorkers() {
        doTest("org.w3c.workers.kt")
    }

    fun testXHR() {
        doTest("org.w3c.xhr.kt")
    }

}
