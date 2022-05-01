package ru.anarchyghost.basketball.modules.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
internal class ConfigServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(ConfigServerApplication::class.java, *args)
}