package ru.anarchyghost.basketball.modules.place.domain

import java.time.Instant
import java.util.*

data class Place(
    val id: UUID,
    val latitude: Double,
    val longitude: Double,
    var name: String,
    var description: String?,
    val address: String?,
    var status: PlaceStatus,
    val kind: PlaceKind,
    var sports: List<SportKind>,
    val createdBy: UUID,
    val approvedBy: UUID?,
    val createdAt: Long,
    var updatedAt: Long,
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
    ) {
        this.name = name
        this.description = description
        this.sports = sports
        setUpdatedAt()
    }

    fun remove() {
        check(this.status != PlaceStatus.REMOVED) { "Place ${this.id} already removed" }
        this.status = PlaceStatus.REMOVED
        setUpdatedAt()
    }

    enum class PlaceStatus {
        UNAPPROVED, APPROVED, REMOVED
    }

    enum class PlaceKind {
        OPEN, CLOSED, NOT_WORKS
    }

    enum class SportKind {
        BASKETBALL
    }

    companion object {
        fun create(
            latitude: Double,
            longitude: Double,
            name: String,
            description: String? = null,
            address: String? = null,
            sports: List<SportKind>,
            createdBy: UUID,
            images: List<UUID>
        ) = Place(
            id = UUID.randomUUID(),
            latitude = latitude,
            longitude = longitude,
            name = name,
            description = description,
            address = address,
            status = PlaceStatus.UNAPPROVED,
            kind = PlaceKind.OPEN,
            createdBy = createdBy,
            approvedBy = null,
            createdAt = Instant.now().toEpochMilli(),
            updatedAt = Instant.now().toEpochMilli(),
            sports = sports,
            images = images.toMutableList()
        )
    }
}