package ru.anarchyghost.basketball.modules.auth.interactions

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "authenticateByCode", name = "auth")
interface AuthenticateByCodeUseCase {
    @GetMapping("/authenticateByCode")
    fun execute(@RequestParam username: String,@RequestParam codeValue: String): AuthenticationDto
}