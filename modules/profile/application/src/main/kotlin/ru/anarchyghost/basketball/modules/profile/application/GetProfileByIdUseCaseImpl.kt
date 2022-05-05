package ru.anarchyghost.basketball.modules.profile.application

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.profile.interactions.GetProfileByIdUseCase
import java.util.UUID

@RestController
internal class GetProfileByIdUseCaseImpl(
    private val profileRepository: ProfileRepository
) : GetProfileByIdUseCase {
    @GetMapping("/getProfileById")
    override fun execute(@RequestParam id: String) = profileRepository.findProfileById(UUID.fromString(id))?.toDto()
}