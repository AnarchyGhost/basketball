package ru.anarchyghost.basketball.modules.sending.application.data

/**
 * Data for notification about new arena
 * @author anarchyghost
 */
data class NewArenaMessageData(
    /**
     * Name of arena
     */
    val arenaName: String,
    /**
     * Address of arena
     */
    val arenaAddress: String
): MessageData