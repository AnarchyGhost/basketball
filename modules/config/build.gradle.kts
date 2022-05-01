plugins {
    id("spring-conventions")
}

tasks.bootJar {
    enabled = true
    archiveClassifier.set("boot")
}

tasks.jar { enabled = true }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-config-server:3.1.2")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.2")
}