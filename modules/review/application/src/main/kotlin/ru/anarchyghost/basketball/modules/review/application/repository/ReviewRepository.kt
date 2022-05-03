package ru.anarchyghost.basketball.modules.review.application.repository

import ru.anarchyghost.basketball.modules.review.domain.Review
import java.util.*

interface ReviewRepository {
    fun findAllReviewsByPlaceIds(ids: Collection<UUID>): List<Review>
    fun findReviewById(id: UUID): Review?
    fun save(review: Review): Review
    fun deleteById(id: UUID)
}