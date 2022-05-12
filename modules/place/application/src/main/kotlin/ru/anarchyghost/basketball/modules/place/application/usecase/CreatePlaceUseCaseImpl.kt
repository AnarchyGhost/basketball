package ru.anarchyghost.basketball.modules.place.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.place.application.mapper.toDto
import ru.anarchyghost.basketball.modules.place.application.repository.PlaceRepository
import ru.anarchyghost.basketball.modules.place.domain.Place
import ru.anarchyghost.basketball.modules.place.interactions.usecase.CreatePlaceUseCase
import java.util.*

@RestController
internal class CreatePlaceUseCaseImpl(
    private val placeRepository: PlaceRepository
) : CreatePlaceUseCase {
    @GetMapping("/createPlace")
    override fun execute(
        @RequestParam latitude: Double,
        @RequestParam longitude: Double,
        @RequestParam name: String,
        @RequestParam description: String?,
        @RequestParam address: String?,
        @RequestParam sports: List<String>,
        @RequestParam createdBy: UUID,
        @RequestParam images: List<String>
    ) = placeRepository.save(
        Place.create(
            latitude = latitude,
            longitude = longitude,
            name = name,
            description = description,
            address = address,
            sports = sports.map { Place.SportKind.valueOf(it) },
            createdBy = createdBy,
            images = images.map { UUID.fromString(it) }
        )
    ).toDto()
}