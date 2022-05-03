package ru.anarchyghost.basketball.modules.event.application.repository

import ru.anarchyghost.basketball.modules.event.domain.Event
import java.util.UUID

interface EventRepository {
    fun findAllEvents(): List<Event>
    fun findEventById(id: UUID): Event?
    fun findAllEventsByIds(ids: Collection<UUID>): List<Event>
    fun save(event: Event): Event
    fun deleteById(id: UUID)
    fun findAllByPlaceIds(ids: Collection<UUID>): List<Event>
}