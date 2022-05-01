plugins {
    id("spring-graphql-conventions")
    id("com.netflix.dgs.codegen")
}

tasks.bootJar {
    enabled = true
    archiveClassifier.set("boot")
}

tasks.jar { enabled = true }

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.2")
    runtimeOnly("org.glassfish.jaxb:jaxb-runtime:4.0.0-M4")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")
    implementation(project(":modules:sending:interactions"))
}