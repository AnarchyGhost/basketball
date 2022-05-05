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
    implementation("org.springframework.cloud:spring-cloud-config-client:3.1.2")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.2")
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter:2.6.7")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.apache.commons:commons-exec:1.3")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("org.glassfish.jaxb:jaxb-runtime:4.0.0-M4")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.2")

    implementation(project(":modules:sending:interactions"))
    implementation(project(":modules:auth:interactions"))
    implementation(project(":modules:place:interactions"))
    implementation(project(":modules:review:interactions"))
    implementation(project(":modules:images:interactions"))
    implementation(project(":modules:profile:interactions"))
    implementation(project(":modules:event:interactions"))
}
repositories {
    mavenCentral()
}
