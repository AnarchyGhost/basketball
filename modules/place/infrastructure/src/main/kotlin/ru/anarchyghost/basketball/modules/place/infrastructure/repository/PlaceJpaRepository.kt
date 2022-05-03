package ru.anarchyghost.basketball.modules.place.infrastructure.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import ru.anarchyghost.basketball.modules.place.application.repository.PlaceRepository
import ru.anarchyghost.basketball.modules.place.domain.Place
import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class PlaceJpa(
    @Id
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val description: String?,
    val address: String?,
    val status: String,
    @ElementCollection
    val sports: List<String>,
    val kind: String,
    val createdBy: String,
    val approvedBy: String?,
    val createdAt: Long,
    val updatedAt: Long,
    @ElementCollection
    val images: List<String>
)

@Repository
interface PlaceJpaRepository : JpaRepository<PlaceJpa, String>

@Component
internal class PlaceRepositoryImpl(
    private val placeJpaRepository: PlaceJpaRepository
) : PlaceRepository {
    companion object {
        fun PlaceJpa.toModel() = Place(
            id = UUID.fromString(id),
            latitude = latitude,
            longitude = longitude,
            name = name,
            description = name,
            address = address,
            status = Place.PlaceStatus.valueOf(status),
            kind = Place.PlaceKind.valueOf(kind),
            createdBy = UUID.fromString(createdBy),
            approvedBy = approvedBy?.let { UUID.fromString(it) },
            createdAt = createdAt,
            updatedAt = updatedAt,
            sports = sports.map { Place.SportKind.valueOf(it) },
            images = images.map { UUID.fromString(it) }.toMutableList()
        )

        fun Place.toJpa() = PlaceJpa(
            id = id.toString(),
            latitude = latitude,
            longitude = longitude,
            name = name,
            description = name,
            address = address,
            status = status.name,
            kind = kind.name,
            createdBy = createdBy.toString(),
            approvedBy = approvedBy?.toString(),
            createdAt = createdAt,
            updatedAt = updatedAt,
            sports = sports.map { it.name },
            images = images.map { it.toString() }
        )
    }

    override fun findAllPlaces() = placeJpaRepository.findAll().map { it.toModel() }

    override fun findPlaceById(id: UUID) = placeJpaRepository.findByIdOrNull(id.toString())?.toModel()

    override fun findAllPlacesByIds(ids: Collection<UUID>) = placeJpaRepository.findAllById(ids.map { it.toString() }).map { it.toModel() }

    override fun save(place: Place) = placeJpaRepository.save(place.toJpa()).toModel()

    override fun deleteById(id: UUID) = placeJpaRepository.deleteById(id.toString())
}