plugins {
    kotlin(Plugins.JVM)
    jacoco
}

dependencies {
    implementation(Dependencies.STDLIB)

    // Test
    testImplementation(TestDependencies.JUNIT)
}
