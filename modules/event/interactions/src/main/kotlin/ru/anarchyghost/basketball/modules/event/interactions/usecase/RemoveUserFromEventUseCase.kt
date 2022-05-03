package ru.anarchyghost.basketball.modules.event.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "removeUserFromEvent", name = "event")
interface RemoveUserFromEventUseCase {
    @GetMapping("/removeUserFromEvent")
    fun execute(
        @RequestParam eventId: String,
        @RequestParam userId: String,
    )
}