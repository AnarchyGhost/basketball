package ru.anarchyghost.basketball.modules.auth.application.usecase

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.auth.Token
import ru.anarchyghost.basketball.modules.auth.TokenGenerator
import ru.anarchyghost.basketball.modules.auth.User
import ru.anarchyghost.basketball.modules.auth.application.CodeExpirationSpecification
import ru.anarchyghost.basketball.modules.auth.application.repository.CodeRepository
import ru.anarchyghost.basketball.modules.auth.application.repository.TokenRepository
import ru.anarchyghost.basketball.modules.auth.application.repository.UserRepository
import ru.anarchyghost.basketball.modules.auth.interactions.AuthenticationDto
import ru.anarchyghost.basketball.modules.auth.interactions.AuthenticateByCodeUseCase

@RestController
class AuthenticateByCodeUseCaseImpl(
    private val tokenGenerator: TokenGenerator,
    private val tokenRepository: TokenRepository,
    private val codeRepository: CodeRepository,
    private val userRepository: UserRepository,
) : AuthenticateByCodeUseCase {

    @GetMapping("/authenticateByCode")
    override fun execute(@RequestParam username: String, @RequestParam codeValue: String): AuthenticationDto {
        val code = codeRepository.findByUsername(username)
            ?: return invalidCodeError()

        if (CodeExpirationSpecification.create().isExpired(code)) {
            codeRepository.delete(code)
            return expiredCodeError()
        }

        if (!code.validate(codeValue)) {
            codeRepository.save(code)
            return invalidCodeError()
        }

        val user = findOrCreateUserByUsername(username)

        val tokenValue = tokenGenerator.generate()
        val token = Token.create(user.id, tokenValue)
        tokenRepository.save(token)

        codeRepository.delete(code)

        return AuthenticationDto(
            token = tokenValue,
            userId = user.id,
        )
    }

    private fun findOrCreateUserByUsername(username: String): User {
            return userRepository.findByPhoneNumber(username)
                ?: userRepository.save(User.create(username))
    }

    private fun invalidCodeError(): AuthenticationDto {
        return AuthenticationDto(
            error = AuthenticationDto.Error(
                code = AuthenticationDto.Error.Code.INVALID,
                message = "Пароль введён неверно."
            )
        )
    }

    private fun expiredCodeError(): AuthenticationDto {
        return AuthenticationDto(
            error = AuthenticationDto.Error(
                code = AuthenticationDto.Error.Code.EXPIRED,
                message = "Срок действия пароля истёк. Запросите новый пароль."
            )
        )
    }

}