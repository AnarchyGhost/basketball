package ru.anarchyghost.basketball.modules.event.application.mapper

import ru.anarchyghost.basketball.modules.event.domain.Event
import ru.anarchyghost.basketball.modules.event.interactions.dto.EventDto

internal fun Event.toDto() = EventDto(
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
    images = images.map { it.toString() },
    users = users.map { it.toString() },
    placeId = placeId?.toString(),
    startDateTime = startDateTime,
    endDateTime = endDateTime
)