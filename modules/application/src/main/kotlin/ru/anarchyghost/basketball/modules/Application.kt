package ru.anarchyghost.basketball.modules

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator

@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator::class)
@EnableEurekaClient
internal class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}