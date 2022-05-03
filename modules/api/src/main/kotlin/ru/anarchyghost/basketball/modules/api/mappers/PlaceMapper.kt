package ru.anarchyghost.basketball.modules.api.mappers

import com.netflix.dgs.codgen.generated.types.*
import ru.anarchyghost.basketball.modules.place.interactions.dto.PlaceDto

internal fun PlaceDto.map() = Place(
    id = id,
    latitude = latitude,
    longitude = longitude,
    name = name,
    description = description,
    address = address,
    createdBy = createdBy,
    sports = sports.map {SportKind.valueOf(it)},
    approvedBy = approvedBy,
    createdAt = createdAt,
    kind = PlaceKind.valueOf(kind),
    status = PlaceStatus.valueOf(status),
    updatedAt = updatedAt,
    reviews = listOf(),
    images = images.map { Image(id = it) }
)
