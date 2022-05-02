package ru.anarchyghost.basketball.modules.place.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "removePlace", name = "place")
interface RemovePlaceUseCase {
    @GetMapping("/removePlace")
    fun execute(
        @RequestParam id: String, @RequestParam userId: String
    )
}