package ru.anarchyghost.basketball.modules.review.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.review.application.mapper.toDto
import ru.anarchyghost.basketball.modules.review.application.repository.ReviewRepository
import ru.anarchyghost.basketball.modules.review.domain.Review
import ru.anarchyghost.basketball.modules.review.interactions.usecase.CreateReviewUseCase
import java.util.*

@RestController
internal class CreateReviewUseCaseImpl(
    private val reviewRepository: ReviewRepository
) : CreateReviewUseCase {
    @GetMapping("/createReview")
    override fun execute(
        @RequestParam placeId: String,
        @RequestParam text: String?,
        @RequestParam createdBy: String,
        @RequestParam rate: Int
    ) = reviewRepository.save(
        Review.create(
            placeId = UUID.fromString(placeId), text = text, createdBy = UUID.fromString(createdBy), rate = rate
        )
    ).toDto()
}