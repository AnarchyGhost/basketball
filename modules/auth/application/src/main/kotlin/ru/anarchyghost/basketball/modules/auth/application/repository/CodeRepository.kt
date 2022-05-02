package ru.anarchyghost.basketball.modules.auth.application.repository

import ru.anarchyghost.basketball.modules.auth.Code

interface CodeRepository {
    fun findByUsername(username: String): Code?
    fun delete(code: Code)
    fun save(code: Code): Code
}