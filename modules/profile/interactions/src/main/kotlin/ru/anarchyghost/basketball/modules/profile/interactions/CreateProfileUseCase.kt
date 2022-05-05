package ru.anarchyghost.basketball.modules.profile.interactions

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "createProfile", name = "profile")
interface CreateProfileUseCase {
    @GetMapping("/createProfile")
    fun execute(
        @RequestParam username: String,
        @RequestParam email: String?,
        @RequestParam imageId: String?
    ): ProfileDto
}