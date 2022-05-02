package ru.anarchyghost.basketball.modules.api.mappers

import com.netflix.dgs.codgen.generated.types.User
import com.netflix.dgs.codgen.generated.types.UserAuthenticationError
import com.netflix.dgs.codgen.generated.types.UserAuthenticationErrorCode
import com.netflix.dgs.codgen.generated.types.UserAuthenticationPayload
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
    phoneNumber = phoneNumber
)