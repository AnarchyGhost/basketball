subprojects {
    group = "ru.anarchyghost.basketball.modules.images"
}

plugins {
    id("spring-conventions")
}
tasks.bootJar {
    enabled = true
    archiveClassifier.set("boot")
}

tasks.jar { enabled = true }

dependencies {
    implementation(project(":modules:images:interactions"))
    implementation(project(":modules:images:application"))
    implementation(project(":modules:images:infrastructure"))
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter:2.6.7")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.2")
    implementation("org.apache.commons:commons-exec:1.3")
    implementation("org.springframework.cloud:spring-cloud-config-client:3.1.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.2")
}
repositories {
    mavenCentral()
}
