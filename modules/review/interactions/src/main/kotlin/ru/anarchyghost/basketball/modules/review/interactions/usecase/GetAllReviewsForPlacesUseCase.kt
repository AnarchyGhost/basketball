package ru.anarchyghost.basketball.modules.review.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.review.interactions.dto.ReviewDto

@FeignClient(contextId = "getAllReviewsForPlaces", name = "review")
interface GetAllReviewsForPlacesUseCase {
    @GetMapping("/getAllReviewsForPlaces")
    fun execute(@RequestParam placeIds: List<String>): List<ReviewDto>
}