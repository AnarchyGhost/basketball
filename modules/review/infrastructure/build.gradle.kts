plugins {
    id("spring-data-conventions")
}

dependencies {
    implementation(project(":modules:review:interactions"))
    implementation(project(":modules:review:application"))
    implementation(project(":modules:review:domain"))
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter:2.6.7")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.apache.commons:commons-exec:1.3")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.2")
}