package ru.anarchyghost.basketball.modules.sending.application.message

/**
 * Message to send by sms
 * @param text text of message
 */
data class SmsNotificationMessage(
    override val text: String
): NotificationMessage
