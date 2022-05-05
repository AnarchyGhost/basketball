package ru.anarchyghost.basketball.modules.api.data

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.auth.interactions.CurrentAuthenticatedUserDto
import ru.anarchyghost.basketball.modules.auth.interactions.GetUsersUseCase

@DgsComponent
internal class UserDataFetcher(
    private val getUsersUseCase: GetUsersUseCase
) {
    @PreAuthorize("isAuthenticated()")
    @DgsQuery
    fun user() =
        getUsersUseCase.execute(listOf((SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId))
            .last().map()
}