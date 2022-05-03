package ru.anarchyghost.basketball.modules.place.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.place.application.mapper.toDto
import ru.anarchyghost.basketball.modules.place.application.repository.PlaceRepository
import ru.anarchyghost.basketball.modules.place.interactions.usecase.RemoveImageFromPlaceUseCase
import java.util.*

@RestController
internal class RemoveImageFromPlaceUseCaseImpl(
    private val placeRepository: PlaceRepository
) : RemoveImageFromPlaceUseCase {
    @GetMapping("/removeImageFromPlace")
    override fun execute(
        @RequestParam placeId: String,
        @RequestParam imageId: String,
        @RequestParam userId: String,
    ) {
        val place = placeRepository . findPlaceById (id = UUID.fromString(placeId))
        check(place?.createdBy.toString() == userId) { "You have no permission to do this action" }
        place?.removeImage(UUID.fromString(imageId))
        place?.let { placeRepository.save(place).toDto() }
    }
}