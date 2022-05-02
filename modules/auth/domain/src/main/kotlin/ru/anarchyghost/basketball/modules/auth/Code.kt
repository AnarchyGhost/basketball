package ru.anarchyghost.basketball.modules.auth

import java.time.Instant

data class Code(
    val username: String,
    var value: String,
    var attempts: Int = 0,
    val createdAt: Long = Instant.now().toEpochMilli(),
    var updatedAt: Long = Instant.now().toEpochMilli()
) {

    companion object{
        fun create(username: String, generator: CodeGenerator): Code {
            return Code(
                username = username,
                value = generator.generate()
            )
        }
    }

    fun renew(codeGenerator: CodeGenerator) {
        value = codeGenerator.generate()
        attempts = 0
        updatedAt = Instant.now().toEpochMilli()
    }

    fun validate(code: String): Boolean {
        attempts++
        return value == code
    }
}