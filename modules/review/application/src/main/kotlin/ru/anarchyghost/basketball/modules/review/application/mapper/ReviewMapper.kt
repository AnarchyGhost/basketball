package ru.anarchyghost.basketball.modules.review.application.mapper

import ru.anarchyghost.basketball.modules.review.domain.Review
import ru.anarchyghost.basketball.modules.review.interactions.dto.ReviewDto

internal fun Review.toDto() = ReviewDto(
    id = id.toString(),
    status = status.name,
    createdBy = createdBy.toString(),
    approvedBy = approvedBy?.toString(),
    createdAt = createdAt.toString(),
    updatedAt = updatedAt.toString(),
    text = text,
    placeId = placeId.toString(),
    rate = rate.rate,
    images = images.map { it.toString() }
)