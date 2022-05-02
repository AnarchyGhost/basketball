package ru.anarchyghost.basketball.modules.api.data

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import org.springframework.security.access.prepost.PreAuthorize
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.place.interactions.usecase.GetAllPlacesUseCase
import ru.anarchyghost.basketball.modules.place.interactions.usecase.GetPlaceByIdUseCase

@DgsComponent
internal class PlaceDataFetcher(
    private val getAllPlacesUseCase: GetAllPlacesUseCase, private val getPlaceByIdUseCase: GetPlaceByIdUseCase
) {
    @PreAuthorize("isAuthenticated()")
    @DgsQuery
    fun places() = getAllPlacesUseCase.execute().map { it.map() }

    @PreAuthorize("isAuthenticated()")
    @DgsQuery
    fun place(id: String) = getPlaceByIdUseCase.execute(id = id)?.map()
}