package ru.anarchyghost.basketball.modules.place.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import ru.anarchyghost.basketball.modules.place.interactions.dto.PlaceDto

@FeignClient(contextId = "getAllPlaces", name = "place")
interface GetAllPlacesUseCase {
    @GetMapping("/getAllPlaces")
    fun execute(): List<PlaceDto>
}