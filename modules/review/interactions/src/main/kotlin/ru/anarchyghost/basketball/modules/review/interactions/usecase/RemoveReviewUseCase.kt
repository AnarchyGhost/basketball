package ru.anarchyghost.basketball.modules.review.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "removeReview", name = "review")
interface RemoveReviewUseCase {
    @GetMapping("/removeReview")
    fun execute(
        @RequestParam id: String, @RequestParam userId: String
    )
}