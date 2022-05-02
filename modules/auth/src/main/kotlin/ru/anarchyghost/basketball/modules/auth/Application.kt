package ru.anarchyghost.basketball.modules.auth

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
internal class AuthApplication

fun main(args: Array<String>) {
    SpringApplication.run(AuthApplication::class.java, *args)
}