package ru.anarchyghost.basketball.modules.auth.interactions

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import java.util.UUID

class TokenAuthenticationDto(
    private val name: String,
    private val credentials: UUID,
    private val principal: CurrentAuthenticatedUserDto,
    private val authorities: List<GrantedAuthority>,
    private val details: UserDto,
    private val authenticated: Boolean
) : Authentication {
    init {
        println("$details")
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