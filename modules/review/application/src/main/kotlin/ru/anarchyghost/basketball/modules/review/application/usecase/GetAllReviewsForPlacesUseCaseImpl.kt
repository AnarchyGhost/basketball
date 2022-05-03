package ru.anarchyghost.basketball.modules.review.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.review.application.mapper.toDto
import ru.anarchyghost.basketball.modules.review.application.repository.ReviewRepository
import ru.anarchyghost.basketball.modules.review.interactions.usecase.GetAllReviewsForPlacesUseCase
import java.util.UUID

@RestController
internal class GetAllReviewsForPlacesUseCaseImpl(
    private val reviewRepository: ReviewRepository
): GetAllReviewsForPlacesUseCase {
    @GetMapping("/getAllReviewsForPlaces")
    override fun execute(@RequestParam placeIds: List<String>) = reviewRepository.findAllReviewsByPlaceIds(ids = placeIds.map { UUID.fromString(it) }).map {it.toDto()}
}