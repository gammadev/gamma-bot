plugins {
    kotlin(Plugins.JVM)
    kotlin(Plugins.KAPT)
}

dependencies {
    implementation(project(Modules.Common.annotations))
    kapt(project(Modules.Common.annotationsProcessor))

    implementation(Dependencies.STDLIB)
    implementation(Dependencies.JDA)
    implementation(Dependencies.KOTLIN_REFLECT)
}