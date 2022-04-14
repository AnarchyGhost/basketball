package ru.anarchyghost.basketball.sending.application.usecase

import ru.anarchyghost.basketball.sending.application.service.SendingService
import ru.anarchyghost.basketball.sending.interactions.usecases.SendReceiptUseCase

/**
 * Use case to send receipt link in message
 * @param sendingService service that sends messages
 * @author anarchyghost
 */
class SendReceiptUseCaseImpl(
    private val sendingService: SendingService
): SendReceiptUseCase {
    /**
     * Sends receipt link in message
     * @param to phone number on which message should be sent
     * @param link receipt link
     * @author anarchyghost
     */
    override fun execute(to: String, link: String) {}
}