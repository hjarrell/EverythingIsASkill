import org.apache.tools.ant.filters.ReplaceTokens
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20-RC"
    id( "com.github.johnrengelman.shadow") version "7.0.0"
}

group = "dev.hunterjarrell.mc"
version = "0.0.1"

repositories {
    mavenCentral()
    maven(
            url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"
    )
    maven(
            url = "https://oss.sonatype.org/content/groups/public/"
    )
    maven(url = "https://jitpack.io")
}

val exposedVersion = "0.31.1"

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    compileOnly("org.xerial:sqlite-jdbc:3.34.0")
}

tasks {
    processResources {
        filter(ReplaceTokens::class, "tokens" to mapOf("version" to project.version))
    }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
