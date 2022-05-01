package ru.anarchyghost.basketball.modules.sending.interactions.events

data class SendAuthCodeEvent(
    val to: String,
    val code: String
)