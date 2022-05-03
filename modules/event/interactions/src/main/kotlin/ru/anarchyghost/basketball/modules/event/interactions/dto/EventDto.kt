package ru.anarchyghost.basketball.modules.event.interactions.dto

data class EventDto(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val description: String?,
    val address: String?,
    val status: String,
    val sports: List<String>,
    val kind: String,
    val createdBy: String,
    val approvedBy: String?,
    val createdAt: Long,
    val updatedAt: Long,
    val images: List<String>,
    val startDateTime: Long,
    val endDateTime: Long,
    val users: List<String>,
    val placeId: String?
)