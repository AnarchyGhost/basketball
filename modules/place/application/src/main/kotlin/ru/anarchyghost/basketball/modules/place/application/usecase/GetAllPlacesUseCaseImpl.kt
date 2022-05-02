package ru.anarchyghost.basketball.modules.place.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.place.application.mapper.toDto
import ru.anarchyghost.basketball.modules.place.application.repository.PlaceRepository
import ru.anarchyghost.basketball.modules.place.interactions.usecase.GetAllPlacesUseCase

@RestController
internal class GetAllPlacesUseCaseImpl(
    private val placeRepository: PlaceRepository
): GetAllPlacesUseCase {
    @GetMapping("/getAllPlaces")
    override fun execute() = placeRepository.findAllPlaces().map {it.toDto()}
}