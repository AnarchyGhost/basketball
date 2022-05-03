package ru.anarchyghost.basketball.modules.images.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.images.application.mapper.toDto
import ru.anarchyghost.basketball.modules.images.application.repository.ImageRepository
import ru.anarchyghost.basketball.modules.images.interactions.usecase.GetImagesByIdsUseCase
import java.util.*

@RestController
internal class GetImagesByIdsUseCaseImpl(
    private val imageRepository: ImageRepository
) : GetImagesByIdsUseCase {
    @GetMapping("/getImagesByIds")
    override fun execute(@RequestParam ids: List<String>) = imageRepository.getAllImagesByIds(ids.map { UUID.fromString(it) }).map { it.toDto() }
}