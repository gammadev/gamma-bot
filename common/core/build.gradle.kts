plugins {
    kotlin(Plugins.JVM)
    jacoco
}

dependencies {
    api(Dependencies.STDLIB)

    implementation(Dependencies.GSON)

    // Test
    testImplementation(TestDependencies.JUNIT)
}
