package ru.anarchyghost.basketball.modules.sending.application.usecase

import org.springframework.stereotype.Service
import ru.anarchyghost.basketball.modules.sending.application.data.AuthCodeMessageData
import ru.anarchyghost.basketball.modules.sending.application.service.SendingService
import ru.anarchyghost.basketball.modules.sending.interactions.usecases.SendAuthCodeUseCase

/**
 * Use case to send auth code
 * @param sendingService service that sends messages
 * @author anarchyghost
 */
@Service
class SendAuthCodeUseCaseImpl(
    private val sendingService: SendingService
) : SendAuthCodeUseCase {
    /**
     * Sends auth code
     * @param to: phone number on which message should be sent
     * @param code: 4-digit auth code
     * @author anarchyghost
     */

    override fun execute(
        to: String,
        code: String
    ) {
        sendingService.send(
            to = to,
            template = "auth/code",
            AuthCodeMessageData(
                code = code
            )
        )
    }
}