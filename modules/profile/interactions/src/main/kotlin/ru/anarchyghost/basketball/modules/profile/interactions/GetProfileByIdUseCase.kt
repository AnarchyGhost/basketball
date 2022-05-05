package ru.anarchyghost.basketball.modules.profile.interactions

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "getProfileById", name = "profile")
interface GetProfileByIdUseCase {
    @GetMapping("/getProfileById")
    fun execute(@RequestParam id: String): ProfileDto?
}