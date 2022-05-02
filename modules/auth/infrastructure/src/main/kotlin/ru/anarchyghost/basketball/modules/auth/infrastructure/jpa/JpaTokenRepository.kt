package ru.anarchyghost.basketball.modules.auth.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import ru.anarchyghost.basketball.modules.auth.Token
import ru.anarchyghost.basketball.modules.auth.application.repository.TokenRepository
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
internal data class JpaToken(
    @Id
    @GeneratedValue
    val id: UUID? = null,
    val hashedValue: String,
    val userId: UUID,
    val createdAt: Long = Instant.now().toEpochMilli(),
    var updatedAt: Long = Instant.now().toEpochMilli(),
    var active: Boolean
)

@Repository
internal interface JpaTokenRepository : JpaRepository<JpaToken, UUID> {
    fun findByHashedValue(hash: String): JpaToken?
    fun findByHashedValueAndActive(hash: String, active: Boolean): JpaToken?
}

@Component
internal class TokenRepositoryJpaImpl(
    private val jpaTokenRepository: JpaTokenRepository,
) : TokenRepository {

    companion object {
        fun Token.map() = JpaToken(
            id = id,
            hashedValue = hashedValue,
            userId = userId,
            createdAt = createdAt,
            updatedAt = updatedAt,
            active = active
        )

        fun JpaToken.map() = Token(
            id = id!!,
            hashedValue = hashedValue,
            userId = userId,
            createdAt = createdAt,
            updatedAt = updatedAt,
            active = active
        )
    }

    override fun getById(id: UUID): Token? {
        return jpaTokenRepository.findByIdOrNull(id)?.map()
    }

    override fun findByHashedValue(value: String): Token? {
        return jpaTokenRepository.findByHashedValue(value)?.map()
    }

    override fun findActiveByHashedValue(value: String): Token? {
        return jpaTokenRepository.findByHashedValueAndActive(value, true)?.map()
    }

    override fun save(token: Token): Token {
        return jpaTokenRepository.save(token.map()).map()
    }
}