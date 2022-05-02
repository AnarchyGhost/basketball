plugins {
    id("spring-conventions")
}

dependencies {
    implementation(project(":modules:auth:interactions"))
    implementation(project(":modules:auth:domain"))
}