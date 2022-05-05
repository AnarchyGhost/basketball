package ru.anarchyghost.basketball.modules.auth.interactions

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@FeignClient(contextId = "assignRoleToUser", name = "auth")
interface AssignRoleToUserUseCase {
    @GetMapping("/assignRoleToUser")
    fun execute(@RequestParam userId: UUID, @RequestParam assignRole: UserPermissionDto, @RequestParam assignTo: UUID)
}