plugins {
    kotlin(Plugins.JVM)
}

dependencies {
    implementation(project(Modules.Common.ANNOTATIONS))

    api(Dependencies.STDLIB)
    api(Dependencies.TAKENOKO)
}
