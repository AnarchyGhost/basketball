package ru.anarchyghost.basketball.modules.review.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.review.interactions.dto.ReviewDto

@FeignClient(contextId = "createReview", name = "review")
interface CreateReviewUseCase {
    @GetMapping("/createReview")
    fun execute(
        @RequestParam placeId: String,
        @RequestParam text: String?,
        @RequestParam createdBy: String,
        @RequestParam rate: Int
    ): ReviewDto
}