package ru.anarchyghost.basketball.modules.review.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.review.application.mapper.toDto
import ru.anarchyghost.basketball.modules.review.application.repository.ReviewRepository
import ru.anarchyghost.basketball.modules.review.interactions.usecase.RemoveImageFromReviewUseCase
import java.util.*

@RestController
internal class RemoveImageFromReviewUseCaseImpl(
    private val reviewRepository: ReviewRepository
) : RemoveImageFromReviewUseCase {
    @GetMapping("/removeImageFromReview")
    override fun execute(
        @RequestParam reviewId: String,
        @RequestParam imageId: String,
        @RequestParam userId: String,
    ) {
        val place = reviewRepository.findReviewById(id = UUID.fromString(reviewId))
        check(place?.createdBy.toString() == userId) { "You have no permission to do this action" }
        place?.removeImage(UUID.fromString(imageId))
        place?.let { reviewRepository.save(place).toDto() }
    }
}