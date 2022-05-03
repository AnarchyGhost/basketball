package ru.anarchyghost.basketball.modules.images.application.usecase

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.images.application.mapper.toDto
import ru.anarchyghost.basketball.modules.images.application.repository.ImageRepository
import ru.anarchyghost.basketball.modules.images.domain.Image
import ru.anarchyghost.basketball.modules.images.interactions.usecase.SaveImageUseCase
import java.util.*

@RestController
internal class SaveImageUseCaseImpl(
    private val imageRepository: ImageRepository
) : SaveImageUseCase {
    @PostMapping("/saveImage")
    override fun execute(@RequestBody image: String, @RequestParam userId: String) = imageRepository.save(
            Image.create(
                image = image, createdBy = UUID.fromString(userId)
            )
        ).toDto()
}