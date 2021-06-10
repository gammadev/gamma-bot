plugins {
    kotlin(Plugins.JVM)
    jacoco
}

dependencies {
    implementation(project(Modules.Common.ANNOTATIONS))

    implementation(Dependencies.STDLIB)
    implementation(Dependencies.TAKENOKO)

    // Test
    testImplementation(TestDependencies.JUNIT)
}
