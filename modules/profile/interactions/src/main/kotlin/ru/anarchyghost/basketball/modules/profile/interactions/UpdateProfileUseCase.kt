package ru.anarchyghost.basketball.modules.profile.interactions

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "updateProfile", name = "profile")
interface UpdateProfileUseCase {
    @GetMapping("/updateProfile")
    fun execute(
        @RequestParam profileId: String,
        @RequestParam username: String,
        @RequestParam email: String?,
        @RequestParam imageId: String?
    ): ProfileDto?
}