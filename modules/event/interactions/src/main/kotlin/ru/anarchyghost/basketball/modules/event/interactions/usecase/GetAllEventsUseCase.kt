package ru.anarchyghost.basketball.modules.event.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import ru.anarchyghost.basketball.modules.event.interactions.dto.EventDto

@FeignClient(contextId = "getAllPlaces", name = "event")
interface GetAllEventsUseCase {
    @GetMapping("/getAllEvents")
    fun execute(): List<EventDto>
}