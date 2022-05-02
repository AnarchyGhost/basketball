package ru.anarchyghost.basketball.modules.auth.interactions.events

data class AuthCodeCreatedEvent(
    val username: String,
    val code: String
)