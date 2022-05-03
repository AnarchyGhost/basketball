package ru.anarchyghost.basketball.modules.event.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.event.application.mapper.toDto
import ru.anarchyghost.basketball.modules.event.application.repository.EventRepository
import ru.anarchyghost.basketball.modules.event.interactions.usecase.GetEventByIdUseCase
import java.util.*

@RestController
internal class GetEventByIdUseCaseImpl(
    private val eventRepository: EventRepository
) : GetEventByIdUseCase {
    @GetMapping("/getEventById")
    override fun execute(id: String) = eventRepository.findEventById(UUID.fromString(id))?.toDto()
}