package ru.anarchyghost.basketball.modules.auth.application.mapper

import ru.anarchyghost.basketball.modules.auth.User
import ru.anarchyghost.basketball.modules.auth.interactions.UserDto

internal fun User.toDto() = UserDto(
    id = id,
    phoneNumber = phoneNumber,
)