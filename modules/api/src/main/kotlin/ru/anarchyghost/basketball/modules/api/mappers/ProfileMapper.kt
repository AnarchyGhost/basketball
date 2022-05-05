package ru.anarchyghost.basketball.modules.api.mappers

import com.netflix.dgs.codgen.generated.types.Image
import com.netflix.dgs.codgen.generated.types.Profile
import ru.anarchyghost.basketball.modules.profile.interactions.ProfileDto

internal fun ProfileDto.map() = Profile(
    id = id,
    username = username,
    email = email,
    image = imageId?.let { Image(id = id) }
)