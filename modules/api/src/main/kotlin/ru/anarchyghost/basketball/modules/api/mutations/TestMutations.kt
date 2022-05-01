package ru.anarchyghost.basketball.modules.api.mutations

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import ru.anarchyghost.basketball.modules.sending.interactions.usecases.SendAuthCodeUseCase

@DgsComponent
class TestMutations(
    private val sendAuthCodeUseCase: SendAuthCodeUseCase
) {
    @DgsMutation
    fun test(): String {
        sendAuthCodeUseCase.execute("","")
        return "OK"
    }
}