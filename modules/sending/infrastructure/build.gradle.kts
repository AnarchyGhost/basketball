plugins {
    id("spring-conventions")
}

dependencies {
    implementation(project(":modules:sending:interactions"))
    implementation(project(":modules:sending:application"))
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.2")
}