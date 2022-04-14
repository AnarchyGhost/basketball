plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("gradle-plugin"))
    implementation(kotlin("allopen"))
    implementation(kotlin("noarg"))
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.4.5")
    implementation("com.netflix.graphql.dgs.codegen:graphql-dgs-codegen-gradle:5.0.6")
}
