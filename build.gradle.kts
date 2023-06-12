buildscript {
    val kotlinVersion = "1.5.30"
    val jacocoVersion = "0.8.7"

    repositories {
        jcenter()
        mavenLocal()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jacoco:org.jacoco.core:$jacocoVersion")
    }
}

plugins {
    id("java")
    id("jacoco")
    id("org.sonarqube").version("3.2.0")
}

allprojects {
    repositories {
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "https://m2.dv8tion.net/releases")
    }
}

sonarqube {
    properties {
        property("sonar.projectName", "Gamma Bot")
        property("sonar.projectKey", "gammadev_gamma-bot")
        property("sonar.organization", "gamma-dev")

        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.login", System.getenv("SONAR_TOKEN"))
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${projectDir.path}/build/reports/jacoco/codeCoverageReport/codeCoverageReport.xml"
        )
    }
}

//apply(from = "${project.rootDir}/settings/sonar.gradle")
apply(from = "${project.rootDir}/settings/jacoco.gradle.kts")
