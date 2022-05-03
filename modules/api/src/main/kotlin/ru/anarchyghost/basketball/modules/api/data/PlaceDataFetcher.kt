package ru.anarchyghost.basketball.modules.api.data

import com.netflix.dgs.codgen.generated.types.Place
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import org.springframework.security.access.prepost.PreAuthorize
import ru.anarchyghost.basketball.modules.api.loaders.AverageRateDataLoader
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.place.interactions.usecase.GetAllPlacesUseCase
import ru.anarchyghost.basketball.modules.place.interactions.usecase.GetPlaceByIdUseCase
import java.util.concurrent.CompletableFuture

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

    @DgsData(parentType = "Place", field = "averageRate")
    fun averageRateForPlace(dfe: DgsDataFetchingEnvironment): CompletableFuture<Int> {
        val id = dfe.getSource<Place>().id
        val dataLoader = dfe.getDataLoader<String, Int>(AverageRateDataLoader::class.java)
        return dataLoader.load(id)
    }
}