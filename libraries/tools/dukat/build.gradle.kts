plugins {
    kotlin("jvm")
}

repositories {
    mavenLocal()
}

dependencies {
    implementation(projectTests(":compiler:tests-common"))

    implementation(kotlinStdlib())
    implementation("org.jetbrains.dukat:dukat:0.0.16")
    implementation("org.jsoup:jsoup:1.8.2")
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