package ru.anarchyghost.basketball.modules.sending.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.sending.application.service.SendingService
import ru.anarchyghost.basketball.modules.sending.interactions.usecases.SendAuthCodeUseCase

/**
 * Use case to send auth code
 * @param sendingService service that sends messages
 * @author anarchyghost
 */
@RestController
class SendAuthCodeUseCaseImpl(
    private val sendingService: SendingService
) : SendAuthCodeUseCase {
    /**
     * Sends auth code
     * @param to: phone number on which message should be sent
     * @param code: 4-digit auth code
     * @author anarchyghost
     */

    @GetMapping("/sendAuthCode")
    override fun execute(
        @RequestParam to: String,
        @RequestParam code: String
    ) {
        println("AAAA")
    }
}