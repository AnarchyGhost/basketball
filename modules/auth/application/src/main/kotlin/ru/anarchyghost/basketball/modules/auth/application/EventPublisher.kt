package ru.anarchyghost.basketball.modules.auth.application

interface EventPublisher {
    fun publishAuthCodeCreated(username: String, code: String)
}