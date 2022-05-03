package ru.anarchyghost.basketball.modules.review.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.review.application.mapper.toDto
import ru.anarchyghost.basketball.modules.review.application.repository.ReviewRepository
import ru.anarchyghost.basketball.modules.review.interactions.usecase.AssignImageToReviewUseCase
import java.util.*

@RestController
internal class AssignImageToReviewUseCaseImpl(
    private val reviewRepository: ReviewRepository
) : AssignImageToReviewUseCase {
    @GetMapping("/assignImageToReview")
    override fun execute(
        @RequestParam reviewId: String,
        @RequestParam imageId: String,
        @RequestParam userId: String,
    ) {
        val review = reviewRepository.findReviewById(id = UUID.fromString(reviewId))
        check(review?.createdBy.toString() == userId) { "You have no permission to do this action" }
        review?.assignImage(UUID.fromString(imageId))
        review?.let { reviewRepository.save(review).toDto() }
    }
}