package ru.anarchyghost.basketball.modules.auth.application.usecase

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.anarchyghost.basketball.modules.auth.Token
import ru.anarchyghost.basketball.modules.auth.User
import ru.anarchyghost.basketball.modules.auth.application.repository.TokenRepository
import ru.anarchyghost.basketball.modules.auth.application.repository.UserRepository
import ru.anarchyghost.basketball.modules.auth.interactions.CurrentAuthenticatedUserDto

interface TokenAuthenticationService {
    fun getAuthentication(@RequestParam tokenValue: String): Authentication?
}


@RestController
internal class TokenAuthenticationServiceImpl(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
) : TokenAuthenticationService {
    @GetMapping("/getAuthentication")
    override fun getAuthentication(@RequestParam tokenValue: String) =
        tokenRepository.findActiveByHashedValue(Token.hashValue(tokenValue))?.let { token ->
            userRepository.findById(token.userId)?.let { user ->
                TokenAuthentication(
                    user,
                )
            }
        }
}

class TokenAuthentication(
    private val details: User,
) : Authentication {

    override fun getName(): String = details.id.toString()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getCredentials(): Any {
        return details.id
    }

    override fun getDetails(): Any {
        return details
    }

    override fun getPrincipal(): Any {
        return CurrentAuthenticatedUserDto(details.id)
    }

    override fun isAuthenticated(): Boolean = true

    override fun setAuthenticated(isAuthenticated: Boolean) {
        TODO("Not yet implemented")
    }
}

