package ru.anarchyghost.basketball.modules.images.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "deleteImage", name = "images")
interface DeleteImageUseCase {
    @GetMapping( "/deleteImage")
    fun execute(@RequestParam id: String, @RequestParam userId: String)
}