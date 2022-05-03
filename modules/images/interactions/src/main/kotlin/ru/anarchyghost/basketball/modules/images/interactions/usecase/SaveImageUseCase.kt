package ru.anarchyghost.basketball.modules.images.interactions.usecase

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "saveImage", name = "images")
interface SaveImageUseCase {
    @GetMapping( "/saveImage")
    fun saveImage(@RequestParam image: String, @RequestParam userId: String)
}