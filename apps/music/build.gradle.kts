plugins {
    kotlin(Plugins.JVM)
    kotlin(Plugins.KAPT)
    idea
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
    api(Dependencies.JDA)
    api(Dependencies.STDLIB)
    api(Dependencies.COROUTINES_CORE)
    api(Dependencies.LAVA_PLAYER)
    api(Dependencies.KOTLIN_REFLECT)

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
