package ru.anarchyghost.basketball.modules.event.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "assignImageToEvent", name = "event")
interface AssignImageToEventUseCase {
    @GetMapping("/assignImageToEvent")
    fun execute(
        @RequestParam eventId: String,
        @RequestParam imageId: String,
        @RequestParam userId: String,
    )
}