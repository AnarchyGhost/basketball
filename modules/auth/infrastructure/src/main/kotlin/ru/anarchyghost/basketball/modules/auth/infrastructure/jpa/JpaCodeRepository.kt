package ru.anarchyghost.basketball.modules.auth.infrastructure.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import ru.anarchyghost.basketball.modules.auth.Code
import ru.anarchyghost.basketball.modules.auth.application.repository.CodeRepository
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
internal data class JpaCode(
    @Id
    val username: String,
    var value: String,
    var attempts: Int = 0,
    val createdAt: Long = Instant.now().toEpochMilli(),
    var updatedAt: Long = Instant.now().toEpochMilli()
)


@Repository
internal interface JpaCodeRepository: JpaRepository<JpaCode, UUID> {
    fun findByUsername(username: String): JpaCode?
}

@Component
internal class JpaCodeRepositoryImpl2(
    private val jpaCodeRepository: JpaCodeRepository
    ): CodeRepository {
    companion object {
        fun JpaCode.map() = Code(
            username = username,
            value = value,
            attempts = attempts,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
        fun Code.map() = JpaCode(
            username = username,
            value = value,
            attempts = attempts,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
    override fun findByUsername(username: String): Code? {
        return jpaCodeRepository.findByUsername(username)?.map()
    }

    override fun delete(code: Code) {
        jpaCodeRepository.delete(code.map())
    }

    override fun save(code: Code): Code {
        return jpaCodeRepository.save(code.map()).map()
    }
}