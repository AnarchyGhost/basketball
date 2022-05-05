package ru.anarchyghost.basketball.modules.auth.application.usecase

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
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
    private val user: User,
) : Authentication {

    override fun getName(): String = user.id.toString()

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return user.permissions.map { SimpleGrantedAuthority(it.name) }.toMutableList()
    }

    override fun getCredentials(): Any {
        return user.id
    }

    override fun getDetails(): Any {
        return user
    }

    override fun getPrincipal(): Any {
        return CurrentAuthenticatedUserDto(user.id)
    }

    override fun isAuthenticated(): Boolean = true

    override fun setAuthenticated(isAuthenticated: Boolean) {
        TODO("Not yet implemented")
    }
}

