package ru.anarchyghost.basketball.modules

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator::class)
@EnableConfigurationProperties
@ConfigurationPropertiesScan
internal class Application

@ConfigurationProperties(prefix = "app")
@ConstructorBinding
internal data class ApplicationProperties(
    val baseUrl: String = ""
)

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}