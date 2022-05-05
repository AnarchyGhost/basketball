package ru.anarchyghost.basketball.modules.auth.interactions

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(contextId = "assignProfileToUser", name = "auth")
interface AssignProfileToUserUseCase {
    @GetMapping("/assignProfileToUser")
    fun execute(@RequestParam userId: UUID, @RequestParam profileId: UUID): UserDto?
}