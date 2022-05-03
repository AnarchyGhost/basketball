package ru.anarchyghost.basketball.modules.images.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.images.interactions.dto.ImageDto

@FeignClient(contextId = "saveImage", name = "images")
interface SaveImageUseCase {
    @PostMapping( "/saveImage")
    fun execute(@RequestBody image: String, @RequestParam userId: String): ImageDto
}