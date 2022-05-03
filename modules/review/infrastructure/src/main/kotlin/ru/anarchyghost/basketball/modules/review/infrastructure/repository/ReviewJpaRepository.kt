package ru.anarchyghost.basketball.modules.review.infrastructure.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import ru.anarchyghost.basketball.modules.review.application.repository.ReviewRepository
import ru.anarchyghost.basketball.modules.review.domain.Rating
import ru.anarchyghost.basketball.modules.review.domain.Review
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class JpaReview(
    @Id
    val id: String,
    val placeId: String,
    var text: String?,
    var rate: Int,
    var status: String,
    val createdBy: String,
    val approvedBy: String?,
    val createdAt: Instant,
    var updatedAt: Instant,
)

@Repository
interface JpaReviewRepository : JpaRepository<JpaReview, String> {
    fun findAllByPlaceIdIn(placeIds: List<String>): List<JpaReview>
}

@Component
internal class ReviewRepositoryImpl(
    private val jpaReviewRepository: JpaReviewRepository
) : ReviewRepository {
    companion object {
        fun JpaReview.toModel() = Review(
            id = UUID.fromString(id),
            status = Review.ReviewStatus.valueOf(status),
            createdBy = UUID.fromString(createdBy),
            approvedBy = approvedBy?.let { UUID.fromString(it) },
            createdAt = createdAt,
            updatedAt = updatedAt,
            text = text,
            rate = Rating(rate),
            placeId = UUID.fromString(placeId)
        )

        fun Review.toJpa() = JpaReview(
            id = id.toString(),
            status = status.name,
            createdBy = createdBy.toString(),
            approvedBy = approvedBy?.toString(),
            createdAt = createdAt,
            updatedAt = updatedAt,
            placeId = placeId.toString(),
            rate = rate.rate,
            text = text
        )
    }

    override fun findReviewById(id: UUID) = jpaReviewRepository.findByIdOrNull(id.toString())?.toModel()

    override fun findAllReviewsByPlaceIds(ids: Collection<UUID>) = jpaReviewRepository.findAllByPlaceIdIn(ids.map { it.toString() }).map { it.toModel() }

    override fun save(review: Review) = jpaReviewRepository.save(review.toJpa()).toModel()

    override fun deleteById(id: UUID) = jpaReviewRepository.deleteById(id.toString())
}