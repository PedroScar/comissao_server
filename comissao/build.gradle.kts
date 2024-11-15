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
    implementation(project(":database"))
}