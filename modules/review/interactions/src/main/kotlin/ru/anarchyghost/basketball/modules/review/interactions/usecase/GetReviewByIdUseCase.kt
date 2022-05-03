package ru.anarchyghost.basketball.modules.review.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.review.interactions.dto.ReviewDto

@FeignClient(contextId = "getReviewById", name = "review")
interface GetReviewByIdUseCase {
    @GetMapping("/getReviewById")
    fun execute(
        @RequestParam id: String
    ): ReviewDto?
}