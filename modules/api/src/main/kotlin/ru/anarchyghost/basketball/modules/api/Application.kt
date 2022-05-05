package ru.anarchyghost.basketball.modules.api

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.kafka.annotation.EnableKafka
import ru.anarchyghost.basketball.modules.auth.interactions.*
import ru.anarchyghost.basketball.modules.event.interactions.usecase.*
import ru.anarchyghost.basketball.modules.images.interactions.usecase.DeleteImageUseCase
import ru.anarchyghost.basketball.modules.images.interactions.usecase.GetImagesByIdsUseCase
import ru.anarchyghost.basketball.modules.images.interactions.usecase.SaveImageUseCase
import ru.anarchyghost.basketball.modules.place.interactions.usecase.*
import ru.anarchyghost.basketball.modules.review.interactions.usecase.CreateReviewUseCase
import ru.anarchyghost.basketball.modules.review.interactions.usecase.GetAllReviewsForPlacesUseCase
import ru.anarchyghost.basketball.modules.review.interactions.usecase.RemoveReviewUseCase
import ru.anarchyghost.basketball.modules.review.interactions.usecase.UpdateReviewUseCase

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
        RemovePlaceUseCase::class,
        CreateReviewUseCase::class,
        UpdateReviewUseCase::class,
        RemoveReviewUseCase::class,
        GetAllReviewsForPlacesUseCase::class,
        AssignImageToPlaceUseCase::class,
        SaveImageUseCase::class,
        RemoveImageFromPlaceUseCase::class,
        DeleteImageUseCase::class,
        GetImagesByIdsUseCase::class,
        CreateEventUseCase::class,
        UpdateEventUseCase::class,
        RemoveEventUseCase::class,
        GetAllEventsByPlaceIdsUseCase::class,
        GetAllEventsUseCase::class,
        GetEventByIdUseCase::class,
        AssignUserToEventUseCase::class,
        RemoveUserFromEventUseCase::class,
        RemoveRoleFromUserUseCase::class,
        AssignRoleToUserUseCase::class,
    ]
)
internal class ApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(ApiApplication::class.java, *args)
}