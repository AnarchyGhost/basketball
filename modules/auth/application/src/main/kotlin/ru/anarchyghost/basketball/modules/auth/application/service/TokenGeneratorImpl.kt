package ru.anarchyghost.basketball.modules.auth.application.service

import org.springframework.stereotype.Service
import ru.anarchyghost.basketball.modules.auth.TokenGenerator
import java.security.SecureRandom
import java.util.*

@Service
class TokenGeneratorImpl(): TokenGenerator {
    private val secureRandom: SecureRandom = SecureRandom() //threadsafe
    private val base64Encoder: Base64.Encoder = Base64.getUrlEncoder() //threadsafe

    override fun generate(): String {
        val randomBytes = ByteArray(24)
        secureRandom.nextBytes(randomBytes)
        return base64Encoder.encodeToString(randomBytes)
    }
}