plugins {
    kotlin("jvm")
    kotlin("kapt")
    idea
}

dependencies {
    api("net.dv8tion:JDA:4.2.0_204")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.21")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    api("com.sedmelluq:lavaplayer:1.3.53")
    api("org.jetbrains.kotlin:kotlin-reflect:1.4.10")

    implementation(project(Modules.Common.core))
    implementation(project(Modules.Common.coreBot))

    implementation(project(Modules.Common.utils))

    implementation(project(Modules.Common.annotations))
    kapt(project(Modules.Common.annotationsProcessor))

    testImplementation("junit:junit:4.12")
}

idea {
    module {
        sourceDirs = sourceDirs + files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main")
        generatedSourceDirs =
            generatedSourceDirs + files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main")
    }
}
