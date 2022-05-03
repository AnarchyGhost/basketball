package ru.anarchyghost.basketball.modules.api.mappers

import com.netflix.dgs.codgen.generated.types.Image
import com.netflix.dgs.codgen.generated.types.Review
import com.netflix.dgs.codgen.generated.types.ReviewStatus
import ru.anarchyghost.basketball.modules.review.interactions.dto.ReviewDto

internal fun ReviewDto.map() = Review(
    id = id,
    createdBy = createdBy,
    approvedBy = approvedBy,
    createdAt = createdAt.toString(),
    status = ReviewStatus.valueOf(status),
    updatedAt = updatedAt.toString(),
    text = text,
    placeId = placeId,
    rate = rate,
    images = images.map { Image(id = it) }
)
