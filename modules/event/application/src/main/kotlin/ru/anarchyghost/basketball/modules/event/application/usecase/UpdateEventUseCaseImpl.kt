package ru.anarchyghost.basketball.modules.event.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.event.application.mapper.toDto
import ru.anarchyghost.basketball.modules.event.application.repository.EventRepository
import ru.anarchyghost.basketball.modules.event.domain.Event
import ru.anarchyghost.basketball.modules.event.interactions.dto.EventDto
import ru.anarchyghost.basketball.modules.event.interactions.usecase.UpdateEventUseCase
import java.util.*

@RestController
internal class UpdateEventUseCaseImpl(
    private val eventRepository: EventRepository
) : UpdateEventUseCase {
    @GetMapping("/updateEvent")
    override fun execute(
        @RequestParam id: String,
        @RequestParam name: String,
        @RequestParam description: String?,
        @RequestParam sports: List<String>,
        @RequestParam userId: String,
        @RequestParam address: String?,
        @RequestParam startDateTime: Long,
        @RequestParam endDateTime: Long,
        @RequestParam latitude: Double,
        @RequestParam longitude: Double,
        @RequestParam placeId: String?
    ): EventDto? {
        val place = eventRepository.findEventById(
            id = UUID.fromString(id)
        )
        check(place?.createdBy.toString() == userId) { "You have no permission to do this action" }
        place?.update(name = name,
            description = description,
            sports = sports.map { Event.SportKind.valueOf(it) },
            startDateTime = startDateTime,
            endDateTime = endDateTime,
            latitude = latitude,
            address = address,
            longitude = longitude,
            placeId = placeId?.let { UUID.fromString(it) })
        return place?.let { eventRepository.save(place).toDto() }
    }
}