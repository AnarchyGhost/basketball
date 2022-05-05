plugins {
    id("spring-conventions")
}

dependencies {
    implementation(project(":modules:profile:interactions"))
    implementation(project(":modules:profile:domain"))
}