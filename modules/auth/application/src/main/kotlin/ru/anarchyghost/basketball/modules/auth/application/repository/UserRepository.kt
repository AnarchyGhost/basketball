package ru.anarchyghost.basketball.modules.auth.application.repository

import ru.anarchyghost.basketball.modules.auth.User
import java.util.*

interface UserRepository {
    fun findById(id: UUID): User?
    fun findAllById(ids: Iterable<UUID>): List<User>
    fun findByPhoneNumber(phoneNumber: String): User?
    fun save(user: User): User
}