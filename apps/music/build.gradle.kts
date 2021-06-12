plugins {
    kotlin(Plugins.JVM)
    kotlin(Plugins.KAPT)
    idea
    jacoco
}

dependencies {
    // Core
    implementation(project(Modules.Common.CORE))
    implementation(project(Modules.Common.CORE_DISCORD))

    // Common
    implementation(project(Modules.Common.UTILS))
    implementation(project(Modules.Common.ANNOTATIONS))
    kapt(project(Modules.Common.ANNOTATIONS_PROCESSOR))

    implementation(project(Modules.Common.Plugins.COMMAND_MANAGER))

    // Dependencies
    implementation(Dependencies.JDA)
    implementation(Dependencies.STDLIB)
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.LAVA_PLAYER)
    implementation(Dependencies.KOTLIN_REFLECT)

    // Jupiter
    testImplementation(TestDependencies.JUNIT_KOTLIN)
    testImplementation(TestDependencies.JUPITER)
    testRuntimeOnly(TestDependencies.JUPITER_ENGINE)

    // MocK
    testImplementation(TestDependencies.MOCKK)
}

idea {
    module {
        sourceDirs = sourceDirs + files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main")
        generatedSourceDirs =
            generatedSourceDirs + files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {

    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"

    kotlinOptions {
        jvmTarget = "1.8"
        apiVersion = "1.4"
        languageVersion = "1.4"
        allWarningsAsErrors = true
    }
}
