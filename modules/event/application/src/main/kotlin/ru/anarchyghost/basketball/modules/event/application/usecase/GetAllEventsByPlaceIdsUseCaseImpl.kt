package ru.anarchyghost.basketball.modules.event.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.event.application.mapper.toDto
import ru.anarchyghost.basketball.modules.event.application.repository.EventRepository
import ru.anarchyghost.basketball.modules.event.interactions.usecase.GetAllEventsByPlaceIdsUseCase
import java.util.*

@RestController
internal class GetAllEventsByPlaceIdsUseCaseImpl(
    private val eventRepository: EventRepository
): GetAllEventsByPlaceIdsUseCase {
    @GetMapping("/getAllEventsByPlaceIds")
    override fun execute(@RequestBody placeIds: List<String>) = eventRepository.findAllByPlaceIds(
        placeIds.map { UUID.fromString(it) }
    ).map { it.toDto() }
}