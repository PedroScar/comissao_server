plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.serialization)
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_20
    targetCompatibility = JavaVersion.VERSION_20
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile> {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_20)
    }
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    api(libs.ktor.server.html)
    api(libs.jetbrains.kotlin.css)
    api(libs.ktor.server.core)
    api(libs.ktor.server.host.common)
    api(libs.ktor.server.status.pages)
    api(libs.ktor.serialization.kotlinx.json)
    api(libs.ktor.server.content.negotiation)
    api(libs.postgresql)
    api(libs.h2)
    api(libs.exposed.core)
    api(libs.exposed.dao)
    api(libs.exposed.jdbc)
    api(libs.ktor.server.netty)
    api(libs.logback.classic)
    api(libs.koin.core)
    api(libs.koin.ktor)
    api(libs.koin.logger)
    api(libs.ktor.server.config.yaml)
}