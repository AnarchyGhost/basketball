package ru.anarchyghost.basketball.modules.auth.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import ru.anarchyghost.basketball.modules.auth.User
import ru.anarchyghost.basketball.modules.auth.application.repository.UserRepository
import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
internal data class JpaUser(
    @Id
    @GeneratedValue
    val id: UUID?,
    @Column(unique = true)
    val phoneNumber: String,
    val createdAt: Long = Instant.now().toEpochMilli(),
    var updatedAt: Long = Instant.now().toEpochMilli()
)

@Repository
internal interface JpaUserRepository : JpaRepository<JpaUser, UUID> {
    fun findByPhoneNumber(phoneNumber: String): JpaUser?
}

@Component
internal class UserRepositoryImpl(
    private val jpaUserRepository: JpaUserRepository,
) : UserRepository {
    companion object {
        fun User.map() = JpaUser(
            id = id,
            phoneNumber = phoneNumber,
        )

        fun JpaUser.map() = User(
            id = id!!,
            phoneNumber = phoneNumber,
        )
    }
    override fun findById(id: UUID): User? {
        return jpaUserRepository.findByIdOrNull(id)?.map()
    }

    override fun findAllById(ids: Iterable<UUID>): List<User> {
        return jpaUserRepository.findAllById(ids).map {it.map()}
    }

    override fun findByPhoneNumber(phoneNumber: String): User? {
        return jpaUserRepository.findByPhoneNumber(phoneNumber)?.map()
    }

    override fun save(user: User): User {
        return jpaUserRepository.save(user.map()).map()
    }

}