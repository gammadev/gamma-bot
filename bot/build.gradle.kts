plugins {
    kotlin(Plugins.JVM)
    kotlin(Plugins.KAPT)
    idea
}

dependencies {
    // Core
    implementation(project(Modules.Common.core))
    implementation(project(Modules.Common.coreDiscord))

    // Common
    implementation(project(Modules.Common.utils))
    implementation(project(Modules.Common.annotations))
    kapt(project(Modules.Common.annotationsProcessor))

    // Plugins
    implementation(project(Modules.Common.Plugins.commandManager))

    // Apps
    implementation(project(Modules.Apps.music))

    // Dependencies
    implementation(Dependencies.JDA)
    implementation(Dependencies.STDLIB)
    implementation(Dependencies.COROUTINES_CORE)
    implementation(Dependencies.LAVA_PLAYER)
    implementation(Dependencies.KOTLIN_REFLECT)

    // Test
    testImplementation(TestDependencies.JUNIT)
}

idea {
    module {
        sourceDirs = sourceDirs + files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main")
        generatedSourceDirs =
            generatedSourceDirs + files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {

    sourceCompatibility = "11"
    targetCompatibility = "11"

    kotlinOptions {
        jvmTarget = "11"
        apiVersion = "1.4"
        languageVersion = "1.4"
        allWarningsAsErrors = true
    }
}