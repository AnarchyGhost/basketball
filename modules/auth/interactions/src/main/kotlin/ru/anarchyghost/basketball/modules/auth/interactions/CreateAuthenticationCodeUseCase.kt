package ru.anarchyghost.basketball.modules.auth.interactions

interface CreateAuthenticationCodeUseCase {

    fun execute(username: String)
}