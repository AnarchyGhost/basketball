package ru.anarchyghost.basketball.sending.application.data

/**
 * Data for notification about rent
 * @author anarchyghost
 */
data class RentMessageData(
    /**
     * Name of rented place
     */
    val place: String,
    /**
     * Date and time of rent in format hh:mm dd-MM-yyyy
     */
    val date: String
): MessageData
