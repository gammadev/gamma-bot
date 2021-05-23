plugins {
    kotlin(Plugins.JVM)
    kotlin(Plugins.KAPT)
    idea
}

dependencies {
    // Core
    implementation(project(Modules.Common.core))
    implementation(project(Modules.Common.coreBot))

    // Common
    implementation(project(Modules.Common.utils))
    implementation(project(Modules.Common.annotations))
    kapt(project(Modules.Common.annotationsProcessor))

    implementation(project(Modules.Common.Plugins.commandManager))

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
