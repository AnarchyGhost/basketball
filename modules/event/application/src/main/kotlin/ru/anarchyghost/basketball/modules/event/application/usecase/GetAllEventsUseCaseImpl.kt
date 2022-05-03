package ru.anarchyghost.basketball.modules.event.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.event.application.mapper.toDto
import ru.anarchyghost.basketball.modules.event.application.repository.EventRepository
import ru.anarchyghost.basketball.modules.event.interactions.usecase.GetAllEventsUseCase

@RestController
internal class GetAllEventsUseCaseImpl(
    private val eventRepository: EventRepository
): GetAllEventsUseCase {
    @GetMapping("/getAllEvents")
    override fun execute() = eventRepository.findAllEvents().map {it.toDto()}
}