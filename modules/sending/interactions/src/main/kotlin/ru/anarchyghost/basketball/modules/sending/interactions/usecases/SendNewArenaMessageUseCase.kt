package ru.anarchyghost.basketball.modules.sending.interactions.usecases

/**
 * Use case to send notification about new arena
 * @author anarchyghost
 */
interface SendNewArenaMessageUseCase {
    /**
     * Sends notification about new arena
     * @param to phone number on which message should be sent
     * @param arenaName name of arena
     * @param arenaAddress address of arena
     */
    fun execute(to: String, arenaName: String, arenaAddress: String)
}