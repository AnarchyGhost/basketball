package ru.anarchyghost.basketball.modules.sending.application.usecase

import ru.anarchyghost.basketball.modules.sending.application.service.SendingService
import ru.anarchyghost.basketball.modules.sending.interactions.usecases.SendNewArenaMessageUseCase

/**
 * Use case to send notification about new arena
 * @param sendingService service that sends messages
 * @author anarchyghost
 */
class SendNewArenaMessageUseCaseImpl(
    private val sendingService: SendingService
): SendNewArenaMessageUseCase {
    /**
     * Sends notification about new arena
     * @param to phone number on which message should be sent
     * @param arenaName name of arena
     * @param arenaAddress address of arena
     * @author anarchyghost
     */
    override fun execute(to: String, arenaName: String, arenaAddress: String) {}
}