plugins {
    kotlin(Plugins.JVM)
    jacoco
}

dependencies {
    api(Dependencies.JDA)
    implementation(Dependencies.STDLIB)

    // Test
    testImplementation(TestDependencies.JUNIT)
}
