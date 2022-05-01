package ru.anarchyghost.basketball.modules.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import ru.anarchyghost.basketball.modules.sending.interactions.usecases.SendAuthCodeUseCase

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackageClasses = [SendAuthCodeUseCase::class])
internal class ApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(ApiApplication::class.java, *args)
}