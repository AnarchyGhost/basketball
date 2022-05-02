package ru.anarchyghost.basketball.modules.place.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.place.application.mapper.toDto
import ru.anarchyghost.basketball.modules.place.application.repository.PlaceRepository
import ru.anarchyghost.basketball.modules.place.domain.Place
import ru.anarchyghost.basketball.modules.place.interactions.dto.PlaceDto
import ru.anarchyghost.basketball.modules.place.interactions.usecase.UpdatePlaceUseCase
import java.util.*

@RestController
internal class UpdatePlaceUseCaseImpl(
    private val placeRepository: PlaceRepository
) : UpdatePlaceUseCase {
    @GetMapping("/updatePlace")
    override fun execute(
        @RequestParam id: String,
        @RequestParam name: String,
        @RequestParam description: String?,
        @RequestParam sports: List<String>,
        @RequestParam userId: String
    ): PlaceDto? {
        val place = placeRepository.findPlaceById(
            id = UUID.fromString(id)
        )
        check(place?.createdBy.toString() == userId) { "You have no permission to do this action" }
        place?.update(name = name, description = description, sports = sports.map { Place.SportKind.valueOf(it) })
        return place?.let { placeRepository.save(place).toDto() }
    }
}