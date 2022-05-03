package ru.anarchyghost.basketball.modules.event.infrastructure.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import ru.anarchyghost.basketball.modules.event.application.repository.EventRepository
import ru.anarchyghost.basketball.modules.event.domain.Event
import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class JpaEvent(
    @Id
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val description: String?,
    val address: String?,
    val status: String,
    @ElementCollection
    val sports: List<String>,
    val kind: String,
    val createdBy: String,
    val approvedBy: String?,
    val createdAt: Long,
    val updatedAt: Long,
    @ElementCollection
    val images: List<String>,
    @ElementCollection
    val users: List<String>,
    val placeId: String?,
    val startDateTime: Long,
    val endDateTime: Long
)

@Repository
interface EventJpaRepository : JpaRepository<JpaEvent, String> {
    fun findAllByPlaceIdIn(placeIds: List<String>): List<JpaEvent>
}

@Component
internal class EventRepositoryImpl(
    private val eventJpaRepository: EventJpaRepository
) : EventRepository {
    companion object {
        fun JpaEvent.toModel() = Event(
            id = UUID.fromString(id),
            latitude = latitude,
            longitude = longitude,
            name = name,
            description = name,
            address = address,
            status = Event.EventStatus.valueOf(status),
            kind = Event.EventKind.valueOf(kind),
            createdBy = UUID.fromString(createdBy),
            approvedBy = approvedBy?.let { UUID.fromString(it) },
            createdAt = createdAt,
            updatedAt = updatedAt,
            sports = sports.map { Event.SportKind.valueOf(it) },
            images = images.map { UUID.fromString(it) }.toMutableList(),
            placeId = placeId?.let { UUID.fromString(it) },
            users = users.map { UUID.fromString(it) }.toMutableList(),
            endDateTime = endDateTime,
            startDateTime = startDateTime
        )

        fun Event.toJpa() = JpaEvent(
            id = id.toString(),
            latitude = latitude,
            longitude = longitude,
            name = name,
            description = name,
            address = address,
            status = status.name,
            kind = kind.name,
            createdBy = createdBy.toString(),
            approvedBy = approvedBy?.toString(),
            createdAt = createdAt,
            updatedAt = updatedAt,
            sports = sports.map { it.name },
            images = images.map { it.toString() },
            endDateTime = endDateTime,
            startDateTime = startDateTime,
            placeId = placeId?.toString(),
            users = users.map { it.toString() }
        )
    }

    override fun findAllEvents() = eventJpaRepository.findAll().map { it.toModel() }

    override fun findEventById(id: UUID) = eventJpaRepository.findByIdOrNull(id.toString())?.toModel()

    override fun findAllEventsByIds(ids: Collection<UUID>) =
        eventJpaRepository.findAllById(ids.map { it.toString() }).map { it.toModel() }

    override fun save(event: Event) = eventJpaRepository.save(event.toJpa()).toModel()

    override fun deleteById(id: UUID) = eventJpaRepository.deleteById(id.toString())

    override fun findAllByPlaceIds(ids: Collection<UUID>) =
        eventJpaRepository.findAllByPlaceIdIn(ids.map { it.toString() }).map { it.toModel() }
}