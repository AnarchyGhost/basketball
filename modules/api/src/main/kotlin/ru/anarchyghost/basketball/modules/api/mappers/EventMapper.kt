package ru.anarchyghost.basketball.modules.api.mappers

import com.netflix.dgs.codgen.generated.types.*
import ru.anarchyghost.basketball.modules.event.interactions.dto.EventDto

internal fun EventDto.map() = Event(
    id = id,
    latitude = latitude,
    longitude = longitude,
    name = name,
    description = description,
    address = address,
    status = EventStatus.valueOf(status),
    kind = EventKind.valueOf(kind),
    sports = sports.map { SportKind.valueOf(it) },
    createdBy = createdBy,
    approvedBy = approvedBy,
    createdAt = createdAt.toString(),
    updatedAt = updatedAt.toString(),
    images = images.map { Image(id = it) },
    users = users,
    placeId = placeId,
    startDateTime = startDateTime.toString(),
    endDateTime = endDateTime.toString()
)