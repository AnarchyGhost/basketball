package ru.anarchyghost.basketball.modules.sending.interactions.usecases

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

/**
 * Use case to send auth code
 * @author anarchyghost
 */
@FeignClient("sending")
interface SendAuthCodeUseCase {
    /**
     * Sends auth code
     * @param to phone number on which code should be sent
     * @param code 4-digit auth code
     */
    @GetMapping("/sendAuthCode")
    fun execute(
        @RequestParam("to") to: String,
        @RequestParam("code") code: String)
}