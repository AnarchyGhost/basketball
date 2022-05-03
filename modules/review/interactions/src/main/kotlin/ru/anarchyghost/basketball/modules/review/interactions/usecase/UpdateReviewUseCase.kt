package ru.anarchyghost.basketball.modules.review.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.review.interactions.dto.ReviewDto

@FeignClient(contextId = "updateReview", name = "review")
interface UpdateReviewUseCase {
    @GetMapping("/updateReview")
    fun execute(
        @RequestParam id: String,
        @RequestParam text: String?,
        @RequestParam rate: Int,
        @RequestParam userId: String
    ): ReviewDto?
}