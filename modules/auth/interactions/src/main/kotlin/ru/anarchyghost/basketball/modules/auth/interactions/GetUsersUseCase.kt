package ru.anarchyghost.basketball.modules.auth.interactions

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(contextId = "getUsers", name = "auth")
interface GetUsersUseCase {
    @GetMapping("/getUsers")
    fun execute(@RequestParam ids: List<UUID>): List<UserDto>
}