package ru.anarchyghost.basketball.modules.place.interactions.dto

data class PlaceDto(
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
    val images: List<String>
)