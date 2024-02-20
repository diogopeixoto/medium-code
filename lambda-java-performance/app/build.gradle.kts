import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(libs.guava)
    implementation("com.amazonaws:aws-lambda-java-core:1.2.3")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("com.github.diogopeixoto.lambda.App")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.named<ShadowJar>("shadowJar") {
    setProperty("zip64", true)
}
