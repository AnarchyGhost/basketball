package ru.anarchyghost.basketball.modules.api.mappers

import com.netflix.dgs.codgen.generated.types.Image
import ru.anarchyghost.basketball.modules.images.interactions.dto.ImageDto

internal fun ImageDto.map() = Image(
    id = id,
    createdBy = createdBy,
    createdAt = createdAt,
    value = image
)
