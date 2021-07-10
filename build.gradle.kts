buildscript {

    repositories {
        jcenter()
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        classpath(Plugins.KOTLIN_GRADLE)
        classpath(Plugins.JACOCO_CORE)
    }
}

plugins {
    id("java")
    id("jacoco")
    id("org.sonarqube") version "3.2.0"
}

allprojects {
    repositories {
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

apply(from = "${project.rootDir}/settings/sonar.gradle")
apply(from = "${project.rootDir}/settings/jacoco.gradle.kts")
