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

    // Plugins
    implementation(project(Modules.Common.Plugins.COMMAND_MANAGER))

    // Apps
    implementation(project(Modules.Apps.MUSIC))

    // Games
    implementation(project(Modules.Games.BATTLESHIP))

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

    kotlinOptions {
        jvmTarget = "11"
        apiVersion = "1.6"
        languageVersion = "1.6"
        allWarningsAsErrors = true
    }
}
