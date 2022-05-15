package ru.anarchyghost.basketball.modules.eureka

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication(exclude = [
    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration::class,
    org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration::class]
)
internal class EurekaServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(EurekaServerApplication::class.java, *args)
}