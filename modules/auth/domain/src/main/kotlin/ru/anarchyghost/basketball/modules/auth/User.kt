package ru.anarchyghost.basketball.modules.auth

import java.util.UUID

data class User(
    val id: UUID,
    val phoneNumber: String,
) {
    companion object {
        fun create(
            phoneNumber: String,
        ) = User(
            phoneNumber = phoneNumber,
            id = UUID.randomUUID()
        )
    }
}