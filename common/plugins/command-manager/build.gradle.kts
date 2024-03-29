plugins {
    kotlin(Plugins.JVM)
    kotlin(Plugins.KAPT)
    jacoco
}

dependencies {
    implementation(project(Modules.Common.CORE))
    implementation(project(Modules.Common.CORE_DISCORD))
    implementation(project(Modules.Common.ANNOTATIONS))
    kapt(project(Modules.Common.ANNOTATIONS_PROCESSOR))

    implementation(Dependencies.STDLIB)
    implementation(Dependencies.JDA)
    implementation(Dependencies.KOTLIN_REFLECT)
    implementation(Dependencies.TAKENOKO)

    // Test
    testImplementation(TestDependencies.JUNIT)
}
