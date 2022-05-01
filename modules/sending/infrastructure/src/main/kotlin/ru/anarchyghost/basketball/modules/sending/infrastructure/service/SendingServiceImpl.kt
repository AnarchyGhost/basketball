package ru.anarchyghost.basketball.modules.sending.infrastructure.service

import org.springframework.stereotype.Component
import ru.anarchyghost.basketball.modules.sending.application.data.MessageData
import ru.anarchyghost.basketball.modules.sending.application.service.SendingService
import ru.anarchyghost.basketball.modules.sending.infrastructure.sender.Sender

/**
 * Service that prepares message and sends it
 * @param sender sender using to send message
 * @param properties properties of sending module
 * @param templateEngine engine used to transform message data by template
 * @author anarchyghost
 */
@Component
internal class SendingServiceImpl(
    private val sender: Sender,
//    private val properties: Any,
//    private val templateEngine: Any
): SendingService {
    /**
     * Sends message with any data to user
     *
     * @param to phone number on which message should be sent
     * @param template name of message template that exists in resources directory
     * @param data data for message
     */
    override fun send(to: String, template: String, data: MessageData) {}

    /**
     * Transforms message data to message
     * @param template name of message template that exists in resources directory
     * @param data data for message
     */
    private fun prepareMessage(template: String, data: MessageData): MessageData {
        TODO()
    }
}