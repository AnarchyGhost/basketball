package ru.anarchyghost.basketball.modules.api.mappers

import com.netflix.dgs.codgen.generated.types.*
import ru.anarchyghost.basketball.modules.auth.interactions.AuthenticationDto
import ru.anarchyghost.basketball.modules.auth.interactions.UserDto

internal fun AuthenticationDto.Error.map() = UserAuthenticationError(
    code = UserAuthenticationErrorCode.valueOf(code.name),
    message = message
)

internal fun AuthenticationDto.map() = UserAuthenticationPayload(
    token = token!!,
    user = User(id = userId!!.toString())
)

internal fun UserDto.map() = User(
    id = id.toString(),
    phoneNumber = phoneNumber,
    userPermissions = permissions.map { it.name },
    profile = profileId?.let { Profile(id = it) }
)