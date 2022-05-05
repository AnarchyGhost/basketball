package ru.anarchyghost.basketball.modules.auth.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.auth.User
import ru.anarchyghost.basketball.modules.auth.application.repository.UserRepository
import ru.anarchyghost.basketball.modules.auth.interactions.RemoveRoleFromUserUseCase
import ru.anarchyghost.basketball.modules.auth.interactions.UserPermissionDto
import java.util.*

@RestController
internal class RemoveRoleFromUserUseCaseImpl(
    private val userRepository: UserRepository
): RemoveRoleFromUserUseCase {
    @GetMapping("/removeRoleFromUser")
    override fun execute(
        @RequestParam userId: UUID,
        @RequestParam removeRole: UserPermissionDto,
        @RequestParam removeFrom: UUID
    ) {
        val currentUser = userRepository.findById(userId)
        currentUser?.let {
            when (removeRole) {
                UserPermissionDto.ADMIN -> throw RuntimeException("You can't remove admin permissions")
                UserPermissionDto.MODER, UserPermissionDto.MANAGER -> check(currentUser.permissions.contains(User.UserPermission.ADMIN)) {"You have no permissions"}
                UserPermissionDto.PLACE_OWNER -> check(currentUser.permissions.contains(User.UserPermission.MANAGER)) {"You have no permissions"}
            }
            val assignToUser = userRepository.findById(removeFrom)
            assignToUser?.let {
                it.removePermission(User.UserPermission.valueOf(removeRole.name))
                userRepository.save(it)
            }
        }
    }
}