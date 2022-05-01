import org.jetbrains.kotlin.gradle.internal.KaptTask

plugins {
    id("kotlin-conventions")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-security")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(platform("org.springframework.cloud:spring-cloud-dependencies:2021.0.2"))
    // Validation
    implementation("javax.validation:validation-api")

    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "junit-vintage-engine")
        exclude(module = "assertj-core")
        exclude(module = "mockito-core")
    }
    testImplementation("com.ninja-squad:springmockk:3.0.1")
}

tasks.bootJar {
    // disable creating bootJar by default
    enabled = false
    // fail on duplicate jar names
    rootSpec.duplicatesStrategy = DuplicatesStrategy.FAIL
}
tasks.jar {
    enabled = true
}


// @see https://youtrack.jetbrains.com/issue/KT-38688

tasks.withType<KaptTask> {
    finalizedBy("copyPropertiesMetadata")
}

task("copyPropertiesMetadata", Copy::class) {
    dependsOn("kaptKotlin")
    from(file("$buildDir/tmp/kapt3/classes/main/META-INF/spring-configuration-metadata.json"))
    into(file("$buildDir/generated/source/kapt/main/META-INF"))
}