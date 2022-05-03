package ru.anarchyghost.basketball.modules.api.loaders

import com.netflix.dgs.codgen.generated.types.Event
import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.MappedBatchLoader
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.event.interactions.usecase.GetAllEventsByPlaceIdsUseCase
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader(name = "reviews")
internal class EventDataLoader(
    private val getAllEventsByPlaceIdsUseCase: GetAllEventsByPlaceIdsUseCase
) : MappedBatchLoader<String, List<Event>> {

    override fun load(keys: Set<String>): CompletionStage<Map<String, List<Event>>> {
        val ids = keys.toList()
        return CompletableFuture.supplyAsync {
            getAllEventsByPlaceIdsUseCase.execute(ids).map { it.map() }.groupBy { it.placeId!! }
        }
    }
}
