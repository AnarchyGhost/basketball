package ru.anarchyghost.basketball.modules.event.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.event.interactions.dto.EventDto

@FeignClient(contextId = "getEventById", name = "event")
interface GetEventByIdUseCase {
    @GetMapping("/getEventById")
    fun execute(
        @RequestParam id: String
    ): EventDto?
}