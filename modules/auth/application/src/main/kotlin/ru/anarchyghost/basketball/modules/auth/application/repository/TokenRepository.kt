package ru.anarchyghost.basketball.modules.auth.application.repository

import ru.anarchyghost.basketball.modules.auth.Token
import java.util.*

interface TokenRepository {
    fun getById(id: UUID): Token?
    fun findByHashedValue(value: String): Token?
    fun findActiveByHashedValue(value: String): Token?
    fun save(token: Token): Token
}