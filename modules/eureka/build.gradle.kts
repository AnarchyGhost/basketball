plugins {
    id("spring-conventions")
}

tasks.bootJar {
    enabled = true
    archiveClassifier.set("boot")
}

tasks.jar { enabled = true }

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:3.1.2")
    runtimeOnly("org.glassfish.jaxb:jaxb-runtime:4.0.0-M4")
}