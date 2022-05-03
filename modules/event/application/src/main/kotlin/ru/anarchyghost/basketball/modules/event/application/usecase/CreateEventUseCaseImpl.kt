package ru.anarchyghost.basketball.modules.event.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.event.application.mapper.toDto
import ru.anarchyghost.basketball.modules.event.application.repository.EventRepository
import ru.anarchyghost.basketball.modules.event.domain.Event
import ru.anarchyghost.basketball.modules.event.interactions.usecase.CreateEventUseCase
import java.util.*

@RestController
internal class CreateEventUseCaseImpl(
    private val eventRepository: EventRepository
) : CreateEventUseCase {
    @GetMapping("/createEvent")
    override fun execute(
        @RequestParam latitude: Double,
        @RequestParam longitude: Double,
        @RequestParam name: String,
        @RequestParam description: String?,
        @RequestParam address: String?,
        @RequestParam sports: List<String>,
        @RequestParam createdBy: String,
        @RequestParam placeId: String?,
        @RequestParam startDateTime: Long,
        @RequestParam endDateTime: Long,
    ) =  eventRepository.save(
        Event.create(
            latitude = latitude,
            longitude = longitude,
            name = name,
            description = description,
            address = address,
            sports = sports.map { Event.SportKind.valueOf(it) },
            createdBy = UUID.fromString(createdBy),
            endDateTime = endDateTime,
            startDateTime = startDateTime,
            placeId = placeId?.let {UUID.fromString(it)}
        )
    ).toDto()
}