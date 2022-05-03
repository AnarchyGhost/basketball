package ru.anarchyghost.basketball.modules.event.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.event.interactions.dto.EventDto

@FeignClient(contextId = "updateEvent", name = "event")
interface UpdateEventUseCase {
    @GetMapping("/updateEvent")
    fun execute(
        @RequestParam id: String,
        @RequestParam name: String,
        @RequestParam description: String? = null,
        @RequestParam sports: List<String>,
        @RequestParam userId: String,
        @RequestParam address: String?,
        @RequestParam startDateTime: Long,
        @RequestParam endDateTime: Long,
        @RequestParam latitude: Double,
        @RequestParam longitude: Double,
        @RequestParam placeId: String?
    ): EventDto?
}