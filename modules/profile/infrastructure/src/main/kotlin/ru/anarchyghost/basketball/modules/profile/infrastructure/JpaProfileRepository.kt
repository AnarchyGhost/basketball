package ru.anarchyghost.basketball.modules.profile.infrastructure

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import ru.anarchyghost.basketball.modules.profile.application.ProfileRepository
import ru.anarchyghost.basketball.modules.profile.domain.Profile
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class JpaProfile(
    @Id
    val id: String,
    @Column(unique = true)
    val username: String,
    @Column(unique = true)
    val email: String?,
    val imageId: String?
)

@Repository
interface JpaProfileRepository: JpaRepository<JpaProfile, String>

@Component
internal class ProfileRepositoryImpl(
    private val jpaProfileRepository: JpaProfileRepository
): ProfileRepository {
    companion object {
        fun JpaProfile.toModel() = Profile(
            id = UUID.fromString(id),
            username = username,
            email = email,
            imageId = imageId?.let { UUID.fromString(it) }
        )

        fun Profile.toJpa() = JpaProfile(
            id = id.toString(),
            username = username,
            email = email,
            imageId = imageId?.toString()
        )
    }
    override fun findProfileById(id: UUID) = jpaProfileRepository.findByIdOrNull(id.toString())?.toModel()

    override fun save(profile: Profile) = jpaProfileRepository.save(profile.toJpa()).toModel()

}