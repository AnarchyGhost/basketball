package ru.anarchyghost.basketball.modules.event.domain

import java.time.Instant
import java.util.*

data class Event(
    val id: UUID,
    var placeId: UUID?,
    var latitude: Double,
    var longitude: Double,
    var name: String,
    var description: String?,
    var address: String?,
    var status: EventStatus,
    val kind: EventKind,
    var sports: List<SportKind>,
    val createdBy: UUID,
    val approvedBy: UUID?,
    val createdAt: Long,
    var updatedAt: Long,
    var startDateTime: Long,
    var endDateTime: Long,
    val users: MutableList<UUID>,
    val images: MutableList<UUID>
) {

    private fun setUpdatedAt() {
        this.updatedAt = Instant.now().toEpochMilli()
    }

    fun assignImage(image: UUID) {
        this.images.add(image)
        setUpdatedAt()
    }

    fun removeImage(image: UUID) {
        this.images.remove(image)
        setUpdatedAt()
    }

    fun update(
        name: String,
        description: String?,
        sports: List<SportKind>,
        startDateTime: Long,
        endDateTime: Long,
        latitude: Double,
        address: String?,
        longitude: Double,
        placeId: UUID?
    ) {
        this.name = name
        this.description = description
        this.sports = sports
        this.startDateTime = startDateTime
        this.endDateTime = endDateTime
        this.latitude = latitude
        this.longitude = longitude
        this.placeId = placeId
        this.address = address
        setUpdatedAt()
    }

    fun remove() {
        check(this.status != EventStatus.REMOVED) { "Event ${this.id} already removed" }
        this.status = EventStatus.REMOVED
        setUpdatedAt()
    }

    enum class EventStatus {
        UNAPPROVED, APPROVED, REMOVED
    }

    enum class EventKind {
        OPEN, CLOSED, NOT_WORKS
    }

    enum class SportKind {
        BASKETBALL
    }

    companion object {
        fun create(
            placeId: UUID?,
            startDateTime: Long,
            endDateTime: Long,
            latitude: Double,
            longitude: Double,
            name: String,
            description: String? = null,
            address: String? = null,
            sports: List<SportKind>,
            createdBy: UUID,
        ) = Event(
            id = UUID.randomUUID(),
            latitude = latitude,
            longitude = longitude,
            name = name,
            description = description,
            address = address,
            status = EventStatus.UNAPPROVED,
            kind = EventKind.OPEN,
            createdBy = createdBy,
            approvedBy = null,
            createdAt = Instant.now().toEpochMilli(),
            updatedAt = Instant.now().toEpochMilli(),
            sports = sports,
            images = mutableListOf(),
            placeId = placeId,
            startDateTime = startDateTime,
            endDateTime = endDateTime,
            users = mutableListOf()
        )
    }
}