object TestDependencies {

    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val JUNIT_KOTLIN = "org.jetbrains.kotlin:kotlin-test-junit:1.5.30"
    const val JUPITER = "org.junit.jupiter:junit-jupiter-api:${Versions.JUPITER}"
    const val JUPITER_ENGINE = "org.junit.jupiter:junit-jupiter-api:${Versions.JUPITER}"
    const val MOCKK = "io.mockk:mockk:${Versions.MOCKK}"

    object Versions {
        const val JUNIT = "4.12"
        const val JUPITER = "5.3.1"
        const val MOCKK = "1.10.2"
    }
}
