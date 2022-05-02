package ru.anarchyghost.basketball.modules.auth

import java.security.MessageDigest
import java.time.Instant
import java.util.*

data class Token(
    val id: UUID,
    val hashedValue: String,
    val userId: UUID,
    val createdAt: Long = Instant.now().toEpochMilli(),
    var updatedAt: Long = Instant.now().toEpochMilli(),
    var active: Boolean = true,
) {
    companion object {
        fun hashValue(value: String): String {
            val md = MessageDigest.getInstance("SHA-256")
            return md.digest(value.encodeToByteArray())
                .fold("") { str, it -> str + "%02x".format(it) }
        }

        fun create(userId: UUID, tokenValue: String): Token {
            return Token(
                id = UUID.randomUUID(),
                hashedValue = hashValue(tokenValue),
                userId = userId,
            )
        }
    }

    fun deactivate() {
        check(active) {"This token already deactivated"}
        active = false
        updatedAt = Instant.now().toEpochMilli()
    }
}