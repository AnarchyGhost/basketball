package ru.anarchyghost.basketball.modules.api.data

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import org.springframework.security.access.prepost.PreAuthorize
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.event.interactions.usecase.GetAllEventsUseCase
import ru.anarchyghost.basketball.modules.event.interactions.usecase.GetEventByIdUseCase

@DgsComponent
internal class EventDataFetcher(
    private val getAllEventsUseCase: GetAllEventsUseCase, private val getEventByIdUseCase: GetEventByIdUseCase
) {
    @PreAuthorize("isAuthenticated()")
    @DgsQuery
    fun places() = getAllEventsUseCase.execute().map { it.map() }

    @PreAuthorize("isAuthenticated()")
    @DgsQuery
    fun place(id: String) = getEventByIdUseCase.execute(id = id)?.map()
}