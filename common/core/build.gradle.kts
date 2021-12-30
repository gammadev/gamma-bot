plugins {
    kotlin(Plugins.JVM)
    jacoco
}

dependencies {
    implementation(Dependencies.STDLIB)
    implementation(Dependencies.GSON)

    // Test
    testImplementation(TestDependencies.JUNIT)
}
