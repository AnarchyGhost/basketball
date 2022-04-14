package ru.anarchyghost.basketball.sending.infrastructure.sender

import ru.anarchyghost.basketball.sending.application.message.NotificationMessage

/**
 * Class used to send message by sms
 * @param props properties of sending module
 * @param smsGateway gateway to sms sending api
 */
class SmsSender(
    private val props: Any,
    private val smsGateway: Any
): Sender {
    /**
     * Sends any message to user by sms
     * @param to phone number on which message should be sent
     * @param message message which should be sent
     */
    override fun send(to: String, message: NotificationMessage) {}
}