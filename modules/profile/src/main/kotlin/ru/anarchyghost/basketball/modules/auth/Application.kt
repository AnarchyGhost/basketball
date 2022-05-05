package ru.anarchyghost.basketball.modules.profile

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
internal class ProfileApplication

fun main(args: Array<String>) {
    SpringApplication.run(ProfileApplication::class.java, *args)
}