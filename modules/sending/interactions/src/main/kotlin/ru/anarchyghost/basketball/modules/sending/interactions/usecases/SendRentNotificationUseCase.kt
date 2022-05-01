package ru.anarchyghost.basketball.modules.sending.interactions.usecases

/**
 * Use case to send notification about rent
 * @author anarchyghost
 */
interface SendRentNotificationUseCase {
    /**
     * Sends notification about rent
     * @param to phone number on which message should be sent
     * @param place place of reny
     * @param date date of rent in format hh:mm dd-MM-yyyy
     */
    fun execute(to: String, place: String, date: String)
}