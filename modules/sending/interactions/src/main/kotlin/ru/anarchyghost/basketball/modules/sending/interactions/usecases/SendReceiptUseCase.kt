package ru.anarchyghost.basketball.modules.sending.interactions.usecases

/**
 * Use case to send receipt link in message
 * @author anarchyghost
 */
interface SendReceiptUseCase {
    /**
     * Sends receipt link in message
     * @param to phone number on which message should be sent
     * @param link receipt link
     */
    fun execute(to: String, link: String)
}