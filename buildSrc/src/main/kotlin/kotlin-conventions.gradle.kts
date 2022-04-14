repositories {
    mavenCentral()
}

plugins {
    `java-library`
    `java-test-fixtures`
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    // Logging
    implementation("io.github.microutils:kotlin-logging:1.12.0")
    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("io.mockk:mockk:1.11.0")
    testImplementation("org.assertj:assertj-core:3.20.2")

    testImplementation("io.kotest:kotest-runner-junit5:4.6.2")
    testImplementation("io.kotest:kotest-assertions-core:4.6.2")
    testImplementation("io.kotest:kotest-property:4.6.2")
}

tasks.withType<Jar> {
    // Prefix jar with group to avoid conflicts in bootJar
    archiveBaseName.set("${project.group}-${project.name}")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
        jvmTarget = "11"
    }
}
