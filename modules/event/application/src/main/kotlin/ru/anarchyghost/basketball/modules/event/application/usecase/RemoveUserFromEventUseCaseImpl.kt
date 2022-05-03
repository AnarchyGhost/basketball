package ru.anarchyghost.basketball.modules.event.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.event.application.mapper.toDto
import ru.anarchyghost.basketball.modules.event.application.repository.EventRepository
import ru.anarchyghost.basketball.modules.event.interactions.usecase.RemoveUserFromEventUseCase
import java.util.*

@RestController
internal class RemoveUserFromEventUseCaseImpl(
    private val eventRepository: EventRepository
) : RemoveUserFromEventUseCase {
    @GetMapping("/removeUserFromEvent")
    override fun execute(
        @RequestParam eventId: String,
        @RequestParam userId: String,
    ) {
        val event = eventRepository.findEventById(id = UUID.fromString(eventId))
        event?.removeUser(UUID.fromString(userId))
        event?.let { eventRepository.save(event).toDto() }
    }
}