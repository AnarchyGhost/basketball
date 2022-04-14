package ru.anarchyghost.basketball.sending.interactions.usecases

/**
 * Use case to send auth code
 * @author anarchyghost
 */
interface SendAuthCodeUseCase {
    /**
     * Sends auth code
     * @param to phone number on which code should be sent
     * @param code 4-digit auth code
     */
    fun execute(to: String, code: String)
}