object Plugins {
    const val JVM = "jvm"
    const val KAPT = "kapt"

    const val JACOCO_CORE = "org.jacoco:org.jacoco.core:${InternalVersions.JACOCO_VERSION}"
    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_VERSION}"

    private object InternalVersions {
        const val JACOCO_VERSION = "0.8.7"
    }
}
