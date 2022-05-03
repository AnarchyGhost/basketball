package ru.anarchyghost.basketball.modules.place.images.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import ru.anarchyghost.basketball.modules.images.application.repository.ImageRepository
import ru.anarchyghost.basketball.modules.images.domain.Image
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class JpaImage(
    @Id
    val id: String,
    val image: String,
    val createdBy: String,
    val createdAt: Instant
)

@Repository
interface JpaImageRepository: JpaRepository<JpaImage, String>

@Component
internal class ImageRepositoryImpl(
    private val jpaImageRepository: JpaImageRepository
): ImageRepository {
    companion object {
        fun Image.toJpa() = JpaImage(
            id = id.toString(),
            image = image,
            createdBy = createdBy.toString(),
            createdAt = createdAt
        )

        fun JpaImage.toModel() = Image(
            id = UUID.fromString(id),
            image = image,
            createdBy = UUID.fromString(createdBy),
            createdAt = createdAt
        )
    }
    override fun save(image: Image) = jpaImageRepository.save(image.toJpa()).toModel()

    override fun getImageById(id: UUID) = jpaImageRepository.findByIdOrNull(id.toString())?.toModel()

    override fun removeImage(id: UUID) = jpaImageRepository.deleteById(id.toString())

}