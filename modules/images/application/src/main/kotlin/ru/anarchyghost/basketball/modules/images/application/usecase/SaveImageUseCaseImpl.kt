package ru.anarchyghost.basketball.modules.images.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.images.application.repository.ImageRepository
import ru.anarchyghost.basketball.modules.images.domain.Image
import ru.anarchyghost.basketball.modules.images.interactions.usecase.SaveImageUseCase
import java.util.UUID

@RestController
internal class SaveImageUseCaseImpl(
    private val imageRepository: ImageRepository
) : SaveImageUseCase {
    @GetMapping("/saveImage")
    override fun saveImage(@RequestParam image: String, @RequestParam userId: String) {
        imageRepository.save(
            Image.create(
                image = image, createdBy = UUID.fromString(userId)
            )
        )
    }
}