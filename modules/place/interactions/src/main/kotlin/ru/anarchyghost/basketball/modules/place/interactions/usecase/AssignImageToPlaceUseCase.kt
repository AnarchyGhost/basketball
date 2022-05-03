package ru.anarchyghost.basketball.modules.place.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "assignImage", name = "place")
interface AssignImageToPlaceUseCase {
    @GetMapping("/assignImageToPlace")
    fun execute(
        @RequestParam placeId: String,
        @RequestParam imageId: String,
        @RequestParam userId: String,
    )
}