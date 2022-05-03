package ru.anarchyghost.basketball.modules.event.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "removeEvent", name = "event")
interface RemoveEventUseCase {
    @GetMapping("/removeEvent")
    fun execute(
        @RequestParam id: String, @RequestParam userId: String
    )
}