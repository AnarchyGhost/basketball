package ru.anarchyghost.basketball.modules.auth.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.auth.application.mapper.toDto
import ru.anarchyghost.basketball.modules.auth.application.repository.UserRepository
import ru.anarchyghost.basketball.modules.auth.interactions.GetUsersUseCase
import ru.anarchyghost.basketball.modules.auth.interactions.UserDto
import java.util.*

@RestController
class GetUsersUseCaseImpl(
    private val userRepository: UserRepository,
) : GetUsersUseCase {
    @GetMapping("/getUsers")
    override fun execute(@RequestParam ids: List<UUID>): List<UserDto> {
        return userRepository.findAllById(ids).map { it.toDto()}
    }
}