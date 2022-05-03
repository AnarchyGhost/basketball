package ru.anarchyghost.basketball.modules.api.data

import com.netflix.dgs.codgen.generated.types.Image
import com.netflix.dgs.codgen.generated.types.Place
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.images.interactions.usecase.GetImagesByIdsUseCase
import java.util.concurrent.CompletableFuture

@DgsComponent
internal class ImageDataFetcher(
    private val getImagesByIdsUseCase: GetImagesByIdsUseCase
) {
    @DgsData(parentType = "Place", field = "images")
    fun imagesForPlace(dfe: DgsDataFetchingEnvironment): CompletableFuture<MutableList<Image>> {
        val ids = dfe.getSource<Place>().images!!.map { it.id }
        return CompletableFuture.supplyAsync {
            getImagesByIdsUseCase.execute(ids).map { it.map() }.toMutableList()
        }
    }
}