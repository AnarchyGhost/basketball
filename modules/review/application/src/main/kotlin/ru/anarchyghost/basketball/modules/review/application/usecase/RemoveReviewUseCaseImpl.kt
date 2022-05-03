package ru.anarchyghost.basketball.modules.review.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.review.application.mapper.toDto
import ru.anarchyghost.basketball.modules.review.application.repository.ReviewRepository
import ru.anarchyghost.basketball.modules.review.interactions.usecase.RemoveReviewUseCase
import java.util.*

@RestController
internal class RemoveReviewUseCaseImpl(
    private val reviewRepository: ReviewRepository
) : RemoveReviewUseCase {
    @GetMapping("/removeReview")
    override fun execute(
        @RequestParam id: String, @RequestParam userId: String
    ) {
        val review = reviewRepository.findReviewById(
            id = UUID.fromString(id)
        )
        check(review?.createdBy.toString() == userId) { "You have no permission to do this action" }
        review?.remove()
        review?.let { reviewRepository.save(review).toDto() }
    }
}