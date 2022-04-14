package ru.anarchyghost.basketball.sending.interactions.usecases

/**
 * Use case to send notification about new event
 * @author anarchyghost
 */
interface SendNewEventMessageUseCase {
    /**
     * Sends notification about new event
     * @param to phone number on which message should be sent
     * @param eventPlace place of event
     * @param eventDate date of event in format hh:mm dd-MM-yyyy
     */
    fun execute(to: String, eventName: String, eventPlace: String, eventDate: String)
}