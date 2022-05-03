package ru.anarchyghost.basketball.modules.images.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.images.application.repository.ImageRepository
import ru.anarchyghost.basketball.modules.images.interactions.usecase.DeleteImageUseCase
import java.util.*

@RestController
internal class DeleteImageUseCaseImpl(
    private val imageRepository: ImageRepository
) : DeleteImageUseCase {
    @GetMapping("/deleteImage")
    override fun execute(@RequestParam id: String, @RequestParam userId: String) {
        imageRepository.getAllImagesByIds(listOf(UUID.fromString(id))).lastOrNull()?.let {
            check(it.createdBy.toString() == userId) { "You have no permission to do this action" }
            imageRepository.removeImage(UUID.fromString(id))
        }
    }
}