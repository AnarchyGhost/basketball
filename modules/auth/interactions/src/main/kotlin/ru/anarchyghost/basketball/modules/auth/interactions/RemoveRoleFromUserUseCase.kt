package ru.anarchyghost.basketball.modules.auth.interactions

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(contextId = "removeRoleFromUser", name = "auth")
interface RemoveRoleFromUserUseCase {
    @GetMapping("/removeRoleFromUser")
    fun execute(@RequestParam userId: UUID, @RequestParam removeRole: UserPermissionDto, @RequestParam removeFrom: UUID)
}