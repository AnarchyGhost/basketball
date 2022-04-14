package ru.anarchyghost.basketball.sending.application.data

/**
 * Data for message with auth code
 * @author anarchyghost
 */
data class AuthCodeMessageData(
    /**
     * 4-digit auth code
     */
    val code: String
): MessageData
