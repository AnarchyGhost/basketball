package ru.anarchyghost.basketball.sending.application.usecase

import ru.anarchyghost.basketball.sending.application.service.SendingService
import ru.anarchyghost.basketball.sending.interactions.usecases.SendAuthCodeUseCase

/**
 * Use case to send auth code
 * @param sendingService service that sends messages
 * @author anarchyghost
 */
class SendAuthCodeUseCaseImpl(
    private val sendingService: SendingService
): SendAuthCodeUseCase {
    /**
     * Sends auth code
     * @param to: phone number on which message should be sent
     * @param code: 4-digit auth code
     * @author anarchyghost
     */
    override fun execute(to: String, code: String) {

    }
}