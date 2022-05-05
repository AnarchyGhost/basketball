package ru.anarchyghost.basketball.modules.api.mutations

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder


@DgsComponent
class TestMutations(
) {
    @DgsMutation
    @PreAuthorize("isAuthenticated()")
    fun test(): String {
        return "${SecurityContextHolder.getContext().authentication.authorities.map { it.authority }}"
    }
}