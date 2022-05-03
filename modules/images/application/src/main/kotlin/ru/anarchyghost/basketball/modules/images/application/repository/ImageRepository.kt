package ru.anarchyghost.basketball.modules.images.application.repository

import ru.anarchyghost.basketball.modules.images.domain.Image
import java.util.*

interface ImageRepository {
    fun save(image: Image): Image
    fun getAllImagesByIds(ids: List<UUID>): List<Image>
    fun removeImage(id: UUID)
}