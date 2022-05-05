package ru.anarchyghost.basketball.modules.api.data

import com.netflix.dgs.codgen.generated.types.Profile
import com.netflix.dgs.codgen.generated.types.User
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.profile.interactions.GetProfileByIdUseCase
import java.util.concurrent.CompletableFuture

@DgsComponent
internal class ProfileDataFetcher(
    private val getProfileByIdUseCase: GetProfileByIdUseCase
) {
    @DgsData(parentType = "User", field = "profile")
    fun imageForProfile(dfe: DgsDataFetchingEnvironment): CompletableFuture<Profile?> {
        val id = dfe.getSource<User>().profile?.id
        return CompletableFuture.supplyAsync {
            id?.let { getProfileByIdUseCase.execute(it)?.map() }
        }
    }
}