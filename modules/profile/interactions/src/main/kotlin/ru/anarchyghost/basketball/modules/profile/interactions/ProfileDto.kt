package ru.anarchyghost.basketball.modules.profile.interactions

data class ProfileDto(
    val id: String,
    val username: String,
    val email: String?,
    val imageId: String?
)
