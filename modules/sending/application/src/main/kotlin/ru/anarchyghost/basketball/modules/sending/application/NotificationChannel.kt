package ru.anarchyghost.basketball.modules.sending.application

interface NotificationChannel{
    companion object{
        const val SMS = "sms"
        const val EMAIL = "email"
    }
}