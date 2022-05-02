package ru.anarchyghost.basketball.modules.auth.interactions

import java.util.*

data class AuthenticationDto(
    val token: String? = null,
    val userId: UUID? = null,
    var error: Error? = null
) {
    data class Error(
        val code: Code,
        val message: String
    ) {
        enum class Code {
            EXPIRED,
            INVALID
        }
    }
}
