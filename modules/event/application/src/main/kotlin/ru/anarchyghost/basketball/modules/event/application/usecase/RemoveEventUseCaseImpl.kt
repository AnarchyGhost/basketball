package ru.anarchyghost.basketball.modules.event.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.event.application.mapper.toDto
import ru.anarchyghost.basketball.modules.event.application.repository.EventRepository
import ru.anarchyghost.basketball.modules.event.interactions.usecase.RemoveEventUseCase
import java.util.*

@RestController
internal class RemoveEventUseCaseImpl(
    private val eventRepository: EventRepository
) : RemoveEventUseCase {
    @GetMapping("/removeEvent")
    override fun execute(
        @RequestParam id: String, @RequestParam userId: String
    ) {
        val event = eventRepository.findEventById(
            id = UUID.fromString(id)
        )
        check(event?.createdBy.toString() == userId) { "You have no permission to do this action" }
        event?.remove()
        event?.let { eventRepository.save(event).toDto() }
    }
}