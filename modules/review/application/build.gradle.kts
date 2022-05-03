plugins {
    id("spring-conventions")
}

dependencies {
    implementation(project(":modules:review:interactions"))
    implementation(project(":modules:review:domain"))
}