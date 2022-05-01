package ru.anarchyghost.basketball.modules.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.kafka.annotation.EnableKafka
import ru.anarchyghost.basketball.modules.sending.interactions.usecases.SendAuthCodeUseCase

@EnableDiscoveryClient
@SpringBootApplication
@EnableKafka
@EnableFeignClients(basePackageClasses = [SendAuthCodeUseCase::class])
internal class ApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(ApiApplication::class.java, *args)
}