package ru.anarchyghost.basketball.modules.auth.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.auth.User
import ru.anarchyghost.basketball.modules.auth.application.repository.UserRepository
import ru.anarchyghost.basketball.modules.auth.interactions.AssignRoleToUserUseCase
import ru.anarchyghost.basketball.modules.auth.interactions.UserPermissionDto
import java.util.*

@RestController
internal class AssignRoleToUserUseCaseImpl(
    private val userRepository: UserRepository
): AssignRoleToUserUseCase {
    @GetMapping("/assignRoleToUser")
    override fun execute(
        @RequestParam userId: UUID,
        @RequestParam assignRole: UserPermissionDto,
        @RequestParam assignTo: UUID
    ) {
        val currentUser = userRepository.findById(userId)
        currentUser?.let {
            when (assignRole) {
                UserPermissionDto.ADMIN -> throw RuntimeException("You can't give admin permissions")
                UserPermissionDto.MODER, UserPermissionDto.MANAGER -> check(currentUser.permissions.contains(User.UserPermission.ADMIN)) {"You have no permissions"}
                UserPermissionDto.PLACE_OWNER -> check(currentUser.permissions.contains(User.UserPermission.MANAGER)) {"You have no permissions"}
            }
            val assignToUser = userRepository.findById(assignTo)
            assignToUser?.let {
                it.assignPermission(User.UserPermission.valueOf(assignRole.name))
                userRepository.save(it)
            }
        }
    }
}