pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "basketball"

fun includeModules(path: String) {
    file(path).listFiles()?.forEach {
        if (it.isDirectory && file("${it.path}/build.gradle.kts").exists()) {
            if (file("${it.path}/src").exists()) {
                // skip empty modules
                include(":${path.replace("/", ":")}:${it.name}")
            }
            includeModules("$path/${it.name}")
        }
    }
}

includeModules("modules")