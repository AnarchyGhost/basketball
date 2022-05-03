package ru.anarchyghost.basketball.modules.place.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "removeImageFromPlace", name = "place")
interface RemoveImageFromPlaceUseCase {
    @GetMapping("/removeImageFromPlace")
    fun execute(
        @RequestParam placeId: String,
        @RequestParam imageId: String,
        @RequestParam userId: String,
    )
}