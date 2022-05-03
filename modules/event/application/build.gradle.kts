plugins {
    id("spring-conventions")
}

dependencies {
    implementation(project(":modules:event:interactions"))
    implementation(project(":modules:event:domain"))
}