package ru.anarchyghost.basketball.modules.place.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.place.interactions.dto.PlaceDto
import java.util.*

@FeignClient(contextId = "createPlace", name = "place")
interface CreatePlaceUseCase {
    @GetMapping("/createPlace")
    fun execute(
        @RequestParam latitude: Double,
        @RequestParam longitude: Double,
        @RequestParam name: String,
        @RequestParam description: String? = null,
        @RequestParam address: String? = null,
        @RequestParam sports: List<String>,
        @RequestParam createdBy: UUID,
        @RequestParam images: List<String>
    ): PlaceDto
}