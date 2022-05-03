package ru.anarchyghost.basketball.modules.review.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.review.application.mapper.toDto
import ru.anarchyghost.basketball.modules.review.application.repository.ReviewRepository
import ru.anarchyghost.basketball.modules.review.interactions.dto.ReviewDto
import ru.anarchyghost.basketball.modules.review.interactions.usecase.UpdateReviewUseCase
import java.util.*

@RestController
internal class UpdateReviewUseCaseImpl(
    private val reviewRepository: ReviewRepository
) : UpdateReviewUseCase {
    @GetMapping("/updateReview")
    override fun execute(
        @RequestParam id: String,
        @RequestParam text: String?,
        @RequestParam rate: Int,
        @RequestParam userId: String
    ): ReviewDto? {
        val review = reviewRepository.findReviewById(
            id = UUID.fromString(id)
        )
        check(review?.createdBy.toString() == userId) { "You have no permission to do this action" }
        review?.update(text = text, rate = rate)
        return review?.let { reviewRepository.save(review).toDto() }
    }
}