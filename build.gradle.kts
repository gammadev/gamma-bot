import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.4.0"
}

repositories {
    mavenLocal()
    jcenter()
    maven(url = "https://jcenter.bintray.com")
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("net.dv8tion:JDA:4.2.0_204")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.4.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("com.sedmelluq:lavaplayer:1.3.53")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.4.10")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.4.21")
    testImplementation("io.mockk:mockk:1.10.2")
}

group = "br.com.gmfonseca"
version = "0.1-alpha"

application {
    mainClassName = "br.com.gmfonseca.DiscordApp"
}

sourceSets {
    main {
        java {
            srcDir("src/main/java")
        }
    }
    test {
        java {
            srcDir("src/test/java")
        }
    }
}

tasks.withType<KotlinCompile> {

    sourceCompatibility = "11"
    targetCompatibility = "11"

    kotlinOptions {
        jvmTarget = "11"
        apiVersion = "1.4"
        languageVersion = "1.4"
        allWarningsAsErrors = true
    }
}
