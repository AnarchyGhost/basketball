package ru.anarchyghost.basketball.modules.place.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.place.application.mapper.toDto
import ru.anarchyghost.basketball.modules.place.application.repository.PlaceRepository
import ru.anarchyghost.basketball.modules.place.interactions.usecase.RemovePlaceUseCase
import java.util.*

@RestController
internal class RemovePlaceUseCaseImpl(
    private val placeRepository: PlaceRepository
) : RemovePlaceUseCase {
    @GetMapping("/removePlace")
    override fun execute(
        @RequestParam id: String, @RequestParam userId: String
    ) {
        val place = placeRepository.findPlaceById(
            id = UUID.fromString(id)
        )
        check(place?.createdBy.toString() == userId) { "You have no permission to do this action" }
        place?.remove()
        place?.let { placeRepository.save(place).toDto() }
    }
}