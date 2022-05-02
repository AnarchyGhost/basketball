package ru.anarchyghost.basketball.modules.place.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.place.application.mapper.toDto
import ru.anarchyghost.basketball.modules.place.application.repository.PlaceRepository
import ru.anarchyghost.basketball.modules.place.interactions.usecase.GetPlaceByIdUseCase
import java.util.*

@RestController
internal class GetPlaceByIdUseCaseImpl(
    private val placeRepository: PlaceRepository
) : GetPlaceByIdUseCase {
    @GetMapping("/getPlaceById")
    override fun execute(id: String) = placeRepository.findPlaceById(UUID.fromString(id))?.toDto()
}