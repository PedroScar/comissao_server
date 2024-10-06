plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17)) // ou 20 se você estiver usando Java 20
    }
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.html)
    implementation(libs.jetbrains.kotlin.css)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.postgresql)
    implementation(libs.h2)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)

    // Adicione as dependências do Koin
    implementation("io.insert-koin:koin-core:3.5.0")
    implementation("io.insert-koin:koin-ktor:3.5.0")
    implementation("io.insert-koin:koin-logger-slf4j:3.5.0")

//    testImplementation(libs.ktor.server.test.host)
//    testImplementation(libs.kotlin.test.junit)
}
