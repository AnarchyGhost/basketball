package ru.anarchyghost.basketball.modules.profile.application

import ru.anarchyghost.basketball.modules.profile.domain.Profile
import ru.anarchyghost.basketball.modules.profile.interactions.ProfileDto

internal fun Profile.toDto() = ProfileDto(
    id = id.toString(),
    email = email,
    username = username,
    imageId = imageId?.toString()
)