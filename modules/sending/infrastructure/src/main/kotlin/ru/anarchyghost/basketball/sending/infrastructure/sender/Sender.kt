package ru.anarchyghost.basketball.sending.infrastructure.sender

import ru.anarchyghost.basketball.sending.application.message.NotificationMessage

/**
 * Class used to send by some gateway, e.g. sms or email
 */
interface Sender {
    /**
     * Sends any message to user
     * @param to phone number on which message should be sent
     * @param message message which should be sent
     */
    fun send(to: String, message: NotificationMessage)
}