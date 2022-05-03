package ru.anarchyghost.basketball.modules.api.mappers

import com.netflix.dgs.codgen.generated.types.Place
import com.netflix.dgs.codgen.generated.types.PlaceKind
import com.netflix.dgs.codgen.generated.types.PlaceStatus
import com.netflix.dgs.codgen.generated.types.SportKind
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
    reviews = listOf()
)
