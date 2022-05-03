package ru.anarchyghost.basketball.modules.images.application.mapper

import ru.anarchyghost.basketball.modules.images.domain.Image
import ru.anarchyghost.basketball.modules.images.interactions.dto.ImageDto

internal fun Image.toDto() = ImageDto(
    id = id.toString(),
    image = image,
    createdBy = createdBy.toString(),
    createdAt = createdAt.toString()
)