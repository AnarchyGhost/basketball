plugins {
    idea
    id("org.jetbrains.dokka") version ("1.6.10")
}

idea {
    module.isDownloadJavadoc = true
    module.isDownloadSources = true
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

subprojects {
    repositories {
        mavenCentral()
    }

    apply {
        plugin( "org.jetbrains.dokka")
    }
}