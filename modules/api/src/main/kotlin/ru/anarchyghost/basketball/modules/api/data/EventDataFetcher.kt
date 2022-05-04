package ru.anarchyghost.basketball.modules.api.data

import com.netflix.dgs.codgen.generated.types.Event
import com.netflix.dgs.codgen.generated.types.Place
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import org.springframework.security.access.prepost.PreAuthorize
import ru.anarchyghost.basketball.modules.api.loaders.EventDataLoader
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.event.interactions.usecase.GetAllEventsUseCase
import ru.anarchyghost.basketball.modules.event.interactions.usecase.GetEventByIdUseCase
import java.util.concurrent.CompletableFuture

@DgsComponent
internal class EventDataFetcher(
    private val getAllEventsUseCase: GetAllEventsUseCase, private val getEventByIdUseCase: GetEventByIdUseCase
) {
    @PreAuthorize("isAuthenticated()")
    @DgsQuery
    fun events() = getAllEventsUseCase.execute().map { it.map() }

    @PreAuthorize("isAuthenticated()")
    @DgsQuery
    fun event(id: String) = getEventByIdUseCase.execute(id = id)?.map()

    @DgsData(parentType = "Place", field = "events")
    fun eventsForPlace(dfe: DgsDataFetchingEnvironment): CompletableFuture<List<Event>> {
        val id = dfe.getSource<Place>().id
        val dataLoader = dfe.getDataLoader<String, List<Event>>(EventDataLoader::class.java)
        return dataLoader.load(id)
    }
}