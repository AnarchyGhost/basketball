package ru.anarchyghost.basketball.modules.review.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "removeImageFromReview", name = "review")
interface RemoveImageFromReviewUseCase {
    @GetMapping("/removeImageFromReview")
    fun execute(
        @RequestParam reviewId: String,
        @RequestParam imageId: String,
        @RequestParam userId: String,
    )
}