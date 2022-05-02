plugins {
    id("spring-conventions")
}

dependencies {
    implementation(project(":modules:place:interactions"))
    implementation(project(":modules:place:domain"))
}