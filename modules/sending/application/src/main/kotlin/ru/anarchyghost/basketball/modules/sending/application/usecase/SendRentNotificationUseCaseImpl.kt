package ru.anarchyghost.basketball.modules.sending.application.usecase

import ru.anarchyghost.basketball.modules.sending.application.service.SendingService
import ru.anarchyghost.basketball.modules.sending.interactions.usecases.SendRentNotificationUseCase

/**
 * Use case to send notification about rent
 * @param sendingService service that sends messages
 * @author anarchyghost
 */
class SendRentNotificationUseCaseImpl(
    private val sendingService: SendingService
): SendRentNotificationUseCase {
    /**
     * Sends notification about rent
     * @param to phone number on which message should be sent
     * @param place place of reny
     * @param date date of rent in format hh:mm dd-MM-yyyy
     * @author anarchyghost
     */
    override fun execute(to: String, place: String, date: String) {}
}