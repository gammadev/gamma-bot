plugins {
    kotlin(Plugins.JVM)
}

dependencies {
    implementation(project(Modules.Common.annotations))

    api(Dependencies.STDLIB)
    api(Dependencies.TAKENOKO)
}