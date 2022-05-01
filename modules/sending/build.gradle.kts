subprojects {
    group = "ru.anarchyghost.basketball.modules.sending"
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
    implementation(project(":modules:sending:interactions"))
    implementation(project(":modules:sending:application"))
    implementation(project(":modules:sending:infrastructure"))
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.2")
}