package ru.anarchyghost.basketball.modules.event.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.event.interactions.dto.EventDto

@FeignClient(contextId = "createEvent", name = "event")
interface CreateEventUseCase {
    @GetMapping("/createEvent")
    fun execute(
        @RequestParam latitude: Double,
        @RequestParam longitude: Double,
        @RequestParam name: String,
        @RequestParam description: String? = null,
        @RequestParam address: String? = null,
        @RequestParam sports: List<String>,
        @RequestParam createdBy: String,
        @RequestParam placeId: String?,
        @RequestParam startDateTime: Long,
        @RequestParam endDateTime: Long,
    ): EventDto
}