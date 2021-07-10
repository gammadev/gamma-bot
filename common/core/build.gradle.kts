import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin(Plugins.JVM)
    jacoco
}

dependencies {
    implementation(Dependencies.STDLIB)
//    implementation("org.junit.jupiter:junit-jupiter:5.7.0")

    // Test
//    testImplementation(platform(TestDependencies.JUNIT_BOM))
    testImplementation(TestDependencies.JUPITER)
    testImplementation(TestDependencies.MOCKK)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}
