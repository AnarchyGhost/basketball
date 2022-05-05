package ru.anarchyghost.basketball.modules.auth.interactions

import java.util.*

data class UserDto(
    val id: UUID,
    val phoneNumber: String? = null,
    val permissions: List<UserPermissionDto>,
    val profileId: String?
)