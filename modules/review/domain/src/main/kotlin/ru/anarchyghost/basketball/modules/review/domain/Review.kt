package ru.anarchyghost.basketball.modules.review.domain

import java.time.Instant
import java.util.*

data class Review(
    val id: UUID,
    val placeId: UUID,
    var text: String?,
    var rate: Rating,
    var status: ReviewStatus,
    val createdBy: UUID,
    val approvedBy: UUID?,
    val createdAt: Long,
    var updatedAt: Long,
    val images: MutableList<UUID>
) {

    private fun setUpdatedAt() {
        this.updatedAt = Instant.now().toEpochMilli()
    }

    fun assignImage(image: UUID) {
        this.images.add(image)
        setUpdatedAt()
    }

    fun removeImage(image: UUID) {
        this.images.remove(image)
        setUpdatedAt()
    }

    fun update(
        text: String?,
        rate: Int,
    ) {
        this.text = text
        this.rate = Rating.create(rate)
        setUpdatedAt()
    }

    fun remove() {
        check(this.status != ReviewStatus.REMOVED) { "Review ${this.id} already removed" }
        this.status = ReviewStatus.REMOVED
        setUpdatedAt()
    }

    enum class ReviewStatus {
        UNAPPROVED, APPROVED, REMOVED
    }

    companion object {
        fun create(
            placeId: UUID,
            text: String? = null,
            createdBy: UUID,
            rate: Int
        ) = Review(
            id = UUID.randomUUID(),
            status = ReviewStatus.UNAPPROVED,
            createdBy = createdBy,
            approvedBy = null,
            createdAt = Instant.now().toEpochMilli(),
            updatedAt = Instant.now().toEpochMilli(),
            text = text,
            rate = Rating(rate = rate),
            placeId = placeId,
            images = mutableListOf()
        )
    }
}