package ru.anarchyghost.basketball.modules.auth.application

import ru.anarchyghost.basketball.modules.auth.Code
import java.time.Duration
import java.time.Instant

class CodeExpirationSpecification(
    private val ttl: Duration,
    private val maxAttempts: Int
) {
    companion object Factory{
        fun create() = CodeExpirationSpecification(
            Duration.ofDays(1),
            3
        )
    }

    fun isExpired(code: Code): Boolean {
        val now = Instant.now().toEpochMilli()
        return code.updatedAt + ttl.toMillis() < now || code.attempts >= maxAttempts
    }
}