package ru.anarchyghost.basketball.modules.sending.infrastructure.sender

import org.springframework.stereotype.Component
import ru.anarchyghost.basketball.modules.sending.application.message.NotificationMessage

/**
 * Class used to send message by sms
 * @param props properties of sending module
 * @param smsGateway gateway to sms sending api
 */
@Component
class SmsSender(
    //TODO: real sms
//    private val props: Any,
//    private val smsGateway: Any
): Sender {
    /**
     * Sends any message to user by sms
     * @param to phone number on which message should be sent
     * @param message message which should be sent
     */
    override fun send(to: String, message: NotificationMessage) {
        println("Message will be send to $to:\n ${message.text}")
    }
}