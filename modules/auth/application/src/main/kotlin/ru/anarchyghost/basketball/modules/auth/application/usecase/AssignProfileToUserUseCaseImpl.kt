package ru.anarchyghost.basketball.modules.auth.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.auth.application.mapper.toDto
import ru.anarchyghost.basketball.modules.auth.application.repository.UserRepository
import ru.anarchyghost.basketball.modules.auth.interactions.AssignProfileToUserUseCase
import java.util.*

@RestController
internal class AssignProfileToUserUseCaseImpl(
    private val userRepository: UserRepository
) : AssignProfileToUserUseCase {

    @GetMapping("/assignProfileToUser")
    override fun execute(@RequestParam userId: UUID, @RequestParam profileId: UUID) =
        userRepository.findById(userId)?.let {
            it.assignProfile(profileId)
            userRepository.save(it)
        }?.toDto()
}