package ru.anarchyghost.basketball.modules.review.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.review.application.mapper.toDto
import ru.anarchyghost.basketball.modules.review.application.repository.ReviewRepository
import ru.anarchyghost.basketball.modules.review.interactions.usecase.GetReviewByIdUseCase
import java.util.*

@RestController
internal class GetReviewByIdUseCaseImpl(
    private val reviewRepository: ReviewRepository
) : GetReviewByIdUseCase {
    @GetMapping("/getReviewById")
    override fun execute(id: String) = reviewRepository.findReviewById(UUID.fromString(id))?.toDto()
}