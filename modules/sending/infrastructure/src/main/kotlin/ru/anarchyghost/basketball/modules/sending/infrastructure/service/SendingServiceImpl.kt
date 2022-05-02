package ru.anarchyghost.basketball.modules.sending.infrastructure.service

import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import ru.anarchyghost.basketball.modules.sending.application.NotificationChannel
import ru.anarchyghost.basketball.modules.sending.application.data.MessageData
import ru.anarchyghost.basketball.modules.sending.application.message.NotificationMessage
import ru.anarchyghost.basketball.modules.sending.application.message.SmsNotificationMessage
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
    private val templateEngine: SpringTemplateEngine,
//    private val properties: Any,
) : SendingService {
    companion object {
        val EmailRegex = Regex("^\\S+@\\S+\\.\\S{2,}$")
        val PhoneRegex = Regex("(^\\+?[87]\\d{10}\$)|(^[9]\\d{9}\$)|(^\\+7 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2})")
    }

    private fun getPath(notificationChannel: String, template: String): String {
        return when (notificationChannel) {
            NotificationChannel.EMAIL -> "$template/email/index.html"
            NotificationChannel.SMS -> "$template/sms/index.txt"
            else -> throw RuntimeException("Wrong channel")
        }
    }

    private fun getChannel(to: String): String {
        return when {
            EmailRegex.matches(to) -> NotificationChannel.EMAIL
            PhoneRegex.matches(to) -> NotificationChannel.SMS
            else -> throw RuntimeException("Wrong channel")
        }
    }

    /**
     * Transforms message data to message
     * @param template name of message template that exists in resources directory
     * @param data data for message
     */
    private fun prepareMessage(notificationChannel: String, template: String, data: MessageData): NotificationMessage {
        val context = Context()
        val templatePath = getPath(notificationChannel, template)
        context.setVariable("data", data)

        return when (notificationChannel) {
            NotificationChannel.EMAIL -> {
                TODO()
            }
            NotificationChannel.SMS -> {
                SmsNotificationMessage(
                    templateEngine.process(templatePath, context)
                )
            }
            else -> {
                throw RuntimeException("Wrong channel")
            }
        }
    }

    /**
     * Sends message with any data to user
     *
     * @param to phone number on which message should be sent
     * @param template name of message template that exists in resources directory
     * @param data data for message
     */
    private fun getSender(channel: String): Sender {
        return when (channel) {
            NotificationChannel.EMAIL -> TODO()
            NotificationChannel.SMS -> sender
            else -> throw RuntimeException("Wrong channel")
        }
    }

    override fun send(to: String, template: String, data: MessageData) {
        val channel = getChannel(to)
        val sender = getSender(channel)
        val notificationMessage = prepareMessage(channel, template, data)
        sender.send(to, notificationMessage)
    }
}


