package ru.anarchyghost.basketball.modules.event.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import ru.anarchyghost.basketball.modules.event.interactions.dto.EventDto

@FeignClient(contextId = "getAllEventsByPlaceIds", name = "event")
interface GetAllEventsByPlaceIdsUseCase {
    @GetMapping("/getAllEventsByPlaceIds")
    fun execute(@RequestBody placeIds: List<String>): List<EventDto>
}