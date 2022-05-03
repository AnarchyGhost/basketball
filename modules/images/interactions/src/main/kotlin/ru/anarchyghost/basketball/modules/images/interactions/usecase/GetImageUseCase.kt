package ru.anarchyghost.basketball.modules.images.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.images.interactions.dto.ImageDto

@FeignClient(contextId = "getImage", name = "images")
interface GetImageUseCase {
    @GetMapping("/getImage")
    fun execute(@RequestParam id: String): ImageDto?
}