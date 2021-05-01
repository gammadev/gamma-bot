plugins {
    kotlin("jvm")
    kotlin("kapt")
    idea
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation(project(":annotation"))
    kapt(project(":annotation-processor"))

    testImplementation("junit:junit:4.12")
}

idea {
    module {
        sourceDirs = sourceDirs + files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main")
        generatedSourceDirs =
            generatedSourceDirs + files("build/generated/source/kapt/main", "build/generated/source/kaptKotlin/main")
    }
}