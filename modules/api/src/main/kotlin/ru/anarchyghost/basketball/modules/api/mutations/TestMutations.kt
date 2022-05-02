package ru.anarchyghost.basketball.modules.api.mutations

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.security.access.prepost.PreAuthorize


@DgsComponent
class TestMutations(
) {
    @DgsMutation
    @PreAuthorize("isAuthenticated()")
    fun test(): String {
        return "OK"
    }
}