package ru.anarchyghost.basketball.modules.place.application.mapper

import ru.anarchyghost.basketball.modules.place.domain.Place
import ru.anarchyghost.basketball.modules.place.interactions.dto.PlaceDto

internal fun Place.toDto() = PlaceDto(
    id = id.toString(),
    latitude = latitude,
    longitude = longitude,
    name = name,
    description = description,
    address = address,
    status = status.name,
    kind = kind.name,
    sports = sports.map { it.name },
    createdBy = createdBy.toString(),
    approvedBy = approvedBy?.toString(),
    createdAt = createdAt,
    updatedAt = updatedAt,
    images = images.map { it.toString() }
)