plugins {
    kotlin(Plugins.JVM)
    jacoco
}

dependencies {
    implementation(Dependencies.STDLIB)
    implementation(Dependencies.JDA)

    // Test
    testImplementation(TestDependencies.JUNIT)
}
