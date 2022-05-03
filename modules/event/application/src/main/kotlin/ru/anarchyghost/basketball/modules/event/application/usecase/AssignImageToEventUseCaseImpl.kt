package ru.anarchyghost.basketball.modules.event.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.event.application.mapper.toDto
import ru.anarchyghost.basketball.modules.event.application.repository.EventRepository
import ru.anarchyghost.basketball.modules.event.interactions.usecase.AssignImageToEventUseCase
import java.util.*

@RestController
internal class AssignImageToEventUseCaseImpl(
    private val eventRepository: EventRepository
) : AssignImageToEventUseCase {
    @GetMapping("/assignImageToEvent")
    override fun execute(
        @RequestParam eventId: String,
        @RequestParam imageId: String,
        @RequestParam userId: String,
    ) {
        val event = eventRepository.findEventById(id = UUID.fromString(eventId))
        check(event?.createdBy.toString() == userId) { "You have no permission to do this action" }
        event?.assignImage(UUID.fromString(imageId))
        event?.let { eventRepository.save(event).toDto() }
    }
}