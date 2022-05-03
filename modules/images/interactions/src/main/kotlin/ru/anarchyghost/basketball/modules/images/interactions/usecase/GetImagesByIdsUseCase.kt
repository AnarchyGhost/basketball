package ru.anarchyghost.basketball.modules.images.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import ru.anarchyghost.basketball.modules.images.interactions.dto.ImageDto

@FeignClient(contextId = "getImagesByIds", name = "images")
interface GetImagesByIdsUseCase {
    @GetMapping("/getImagesByIds")
    fun execute(@RequestParam ids: List<String>): List<ImageDto>
}