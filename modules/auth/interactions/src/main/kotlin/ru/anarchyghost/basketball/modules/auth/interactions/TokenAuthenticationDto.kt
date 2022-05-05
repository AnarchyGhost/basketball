package ru.anarchyghost.basketball.modules.auth.interactions

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import java.util.*

class TokenAuthenticationDto(
    private val name: String,
    private val credentials: UUID,
    private val principal: CurrentAuthenticatedUserDto,
    private val authorities: List<AuthDto>,
    private val details: UserDto,
    private val authenticated: Boolean
) : Authentication {

    class AuthDto(private val authority: String): GrantedAuthority {
        override fun getAuthority(): String {
            return authority
        }
    }

    override fun getName(): String = name

    override fun getAuthorities() = authorities

    override fun getCredentials() = credentials

    override fun getDetails() = details

    override fun getPrincipal() = principal

    override fun isAuthenticated() = authenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        TODO("Not yet implemented")
    }
}