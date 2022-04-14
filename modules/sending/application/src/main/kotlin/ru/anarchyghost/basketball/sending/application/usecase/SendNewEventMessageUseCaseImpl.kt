package ru.anarchyghost.basketball.sending.application.usecase

import ru.anarchyghost.basketball.sending.application.service.SendingService
import ru.anarchyghost.basketball.sending.interactions.usecases.SendNewEventMessageUseCase

/**
 * Use case to send notification about new event
 * @param sendingService service that sends messages
 * @author anarchyghost
 */
class SendNewEventMessageUseCaseImpl(
    private val sendingService: SendingService
): SendNewEventMessageUseCase {
    /**
     * Sends notification about new event
     * @param to phone number on which message should be sent
     * @param eventPlace place of event
     * @param eventDate date of event in format hh:mm dd-MM-yyyy
     * @author anarchyghost
     */
    override fun execute(to: String, eventName: String, eventPlace: String, eventDate: String) {}
}