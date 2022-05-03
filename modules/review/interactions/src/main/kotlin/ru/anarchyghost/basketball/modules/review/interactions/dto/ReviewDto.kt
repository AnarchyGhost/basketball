package ru.anarchyghost.basketball.modules.review.interactions.dto


data class ReviewDto(
    val id: String,
    val placeId: String,
    var text: String?,
    var rate: Int,
    var status: String,
    val createdBy: String,
    val approvedBy: String?,
    val createdAt: String,
    var updatedAt: String,
)