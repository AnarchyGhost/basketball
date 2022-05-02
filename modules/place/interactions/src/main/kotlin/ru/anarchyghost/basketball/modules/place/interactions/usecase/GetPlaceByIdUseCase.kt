package ru.anarchyghost.basketball.modules.place.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.place.interactions.dto.PlaceDto

@FeignClient(contextId = "getPlaceById", name = "place")
interface GetPlaceByIdUseCase {
    @GetMapping("/getPlaceById")
    fun execute(
        @RequestParam id: String
    ): PlaceDto?
}