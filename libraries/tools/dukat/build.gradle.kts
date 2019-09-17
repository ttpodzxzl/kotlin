plugins {
    kotlin("jvm")
}

repositories {
    maven("https://kotlin.bintray.com/dukat")
}

dependencies {
    implementation(kotlinStdlib())
    implementation("org.jetbrains.dukat:dukat:0.0.17")
    implementation("org.jsoup:jsoup:1.8.2")

    testCompile(projectTests(":idea"))
    testCompile(projectTests(":compiler:tests-common"))
    testCompile(intellijCoreDep()) { includeJars("intellij-core") }
}

task("downloadIDL", JavaExec::class) {
    main = "org.jetbrains.kotlin.tools.dukat.DownloadKt"
    classpath = sourceSets["main"].runtimeClasspath
    dependsOn(":dukat:build")
}

task("launchDukat", JavaExec::class) {
    main = "org.jetbrains.kotlin.tools.dukat.LaunchKt"
    classpath = sourceSets["main"].runtimeClasspath
    dependsOn(":dukat:build")
}

projectTest {
    doFirst {
        workingDir = rootDir
        systemProperty("idea.home.path", intellijRootDir().canonicalPath)
    }
}