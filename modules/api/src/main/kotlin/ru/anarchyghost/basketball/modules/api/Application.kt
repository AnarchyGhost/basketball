package ru.anarchyghost.basketball.modules.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.kafka.annotation.EnableKafka
import ru.anarchyghost.basketball.modules.auth.interactions.AuthenticateByCodeUseCase
import ru.anarchyghost.basketball.modules.auth.interactions.GetAuthUseCase
import ru.anarchyghost.basketball.modules.auth.interactions.GetUsersUseCase
import ru.anarchyghost.basketball.modules.place.interactions.usecase.*

@EnableDiscoveryClient

@SpringBootApplication
@EnableKafka
@EnableFeignClients(
    basePackageClasses = [
        GetUsersUseCase::class,
        AuthenticateByCodeUseCase::class,
        GetAuthUseCase::class,
        CreatePlaceUseCase::class,
        GetAllPlacesUseCase::class,
        UpdatePlaceUseCase::class,
        GetPlaceByIdUseCase::class,
        RemovePlaceUseCase::class
    ]
)
internal class ApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(ApiApplication::class.java, *args)
}