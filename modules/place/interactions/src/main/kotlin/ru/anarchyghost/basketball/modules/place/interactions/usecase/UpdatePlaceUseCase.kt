package ru.anarchyghost.basketball.modules.place.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.place.interactions.dto.PlaceDto

@FeignClient(contextId = "updatePlace", name = "place")
interface UpdatePlaceUseCase {
    @GetMapping("/updatePlace")
    fun execute(
        @RequestParam id: String,
        @RequestParam name: String,
        @RequestParam description: String? = null,
        @RequestParam sports: List<String>,
        @RequestParam userId: String
    ): PlaceDto?
}