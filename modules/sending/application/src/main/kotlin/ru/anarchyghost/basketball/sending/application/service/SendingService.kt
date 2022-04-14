package ru.anarchyghost.basketball.sending.application.service

import ru.anarchyghost.basketball.sending.application.data.MessageData

/**
 * Service that prepares message and sends it
 * @author anarchyghost
 */
interface SendingService {
    /**
     * Sends message with any data to user
     *
     * @param to phone number on which message should be sent
     * @param template name of message template that exists in resources directory
     * @param data data for message
     */
    fun send(to: String, template: String, data: MessageData)
}