package ru.anarchyghost.basketball.modules.images.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.images.application.mapper.toDto
import ru.anarchyghost.basketball.modules.images.application.repository.ImageRepository
import ru.anarchyghost.basketball.modules.images.interactions.usecase.GetImageUseCase
import java.util.*

@RestController
internal class GetImageUseCaseImpl(
    private val imageRepository: ImageRepository
) : GetImageUseCase {
    @GetMapping("/getImage")
    override fun execute(@RequestParam id: String) = imageRepository.getImageById(id = UUID.fromString(id))?.toDto()
}