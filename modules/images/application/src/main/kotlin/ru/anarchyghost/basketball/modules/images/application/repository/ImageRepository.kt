package ru.anarchyghost.basketball.modules.images.application.repository

import ru.anarchyghost.basketball.modules.images.domain.Image
import java.util.*

interface ImageRepository {
    fun save(image: Image): Image
    fun getImageById(id: UUID): Image?
    fun removeImage(id: UUID)
}