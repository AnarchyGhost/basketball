package ru.anarchyghost.basketball.modules.api.loaders

import com.netflix.dgs.codgen.generated.types.User
import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.MappedBatchLoader
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.auth.interactions.GetUsersUseCase
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader(name = "users")
internal class UserDataLoader(
    private val getUsersUseCase: GetUsersUseCase
): MappedBatchLoader<String, User> {

    override fun load(keys: Set<String>): CompletionStage<Map<String, User>> {
        val ids = keys.map { UUID.fromString(it) }
        return CompletableFuture.supplyAsync {
            getUsersUseCase.execute(ids).map{it.map()}.associateBy { it.id }
        }
    }
}
