package ru.anarchyghost.basketball.modules.review

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
internal class ReviewApplication

fun main(args: Array<String>) {
    SpringApplication.run(ReviewApplication::class.java, *args)
}