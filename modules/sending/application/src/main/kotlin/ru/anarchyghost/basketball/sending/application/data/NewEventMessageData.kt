package ru.anarchyghost.basketball.sending.application.data

/**
 * Data for notification about new event
 * @author anarchyghost
 */
data class NewEventMessageData(
    /**
     * Name of event
     */
    val eventName: String,
    /**
     * Name and address of arena
     */
    val eventPlace: String,
    /**
     * Date of event in format hh:mm dd-MM-yyyy
     */
    val eventDate: String,
): MessageData
