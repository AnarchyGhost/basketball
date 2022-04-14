package ru.anarchyghost.basketball.sending.application.data

/**
 * Data for notification about new receipt
 * @author anarchyghost
 */
data class ReceiptData(
    /**
     * Shortened link to receipt
     */
    val link: String
): MessageData
