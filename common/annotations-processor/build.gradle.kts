plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(Modules.Common.annotations))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.github.yanex:takenoko:0.1")
}