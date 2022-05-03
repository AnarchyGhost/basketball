package ru.anarchyghost.basketball.modules.images.domain

import java.time.Instant
import java.util.UUID

data class Image(
    val id: UUID,
    val image: String,
    val createdBy: UUID,
    val createdAt: Long
) {
    companion object {
        fun create(
            image: String,
            createdBy: UUID,
        ) =
            Image(
                id = UUID.randomUUID(),
                image = image,
                createdBy = createdBy,
                createdAt = Instant.now().toEpochMilli()
            )
    }
}