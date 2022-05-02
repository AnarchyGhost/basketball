plugins {
    id("spring-conventions")
}

dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")
    implementation(project(":modules:auth:domain"))
}
