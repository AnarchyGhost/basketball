package ru.anarchyghost.basketball.modules.profile.application

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.profile.interactions.UpdateProfileUseCase
import java.util.*

@RestController
internal class UpdateProfileUseCaseImpl(
    private val profileRepository: ProfileRepository
): UpdateProfileUseCase {

    @GetMapping("/updateProfile")
    override fun execute(
        @RequestParam profileId: String,
        @RequestParam username: String,
        @RequestParam email: String?,
        @RequestParam imageId: String?
    ) =
        profileRepository.findProfileById(UUID.fromString(profileId))?.let {
            it.update(
                username = username,
                email = email,
                imageId = imageId?.let {img->UUID.fromString(img)}
            )
            profileRepository.save(it).toDto()
        }
}