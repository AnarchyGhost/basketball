package ru.anarchyghost.basketball.modules.profile.application

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.profile.domain.Profile
import ru.anarchyghost.basketball.modules.profile.interactions.CreateProfileUseCase
import java.util.*

@RestController
internal class CreateProfileUseCaseImpl(
    private val profileRepository: ProfileRepository
) : CreateProfileUseCase {
    @GetMapping("/createProfile")
    override fun execute(
        @RequestParam username: String, @RequestParam email: String?, @RequestParam imageId: String?
    ) = profileRepository.save(
        Profile.create(username = username, email = email, imageId = imageId?.let { UUID.fromString(it) })
    ).toDto()
}