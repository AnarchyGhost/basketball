package ru.anarchyghost.basketball.modules.api.data

import com.netflix.dgs.codgen.generated.types.*
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

    @DgsData(parentType = "Review", field = "images")
    fun imagesForReview(dfe: DgsDataFetchingEnvironment): CompletableFuture<MutableList<Image>> {
        val ids = dfe.getSource<Review>().images!!.map { it.id }
        return CompletableFuture.supplyAsync {
            getImagesByIdsUseCase.execute(ids).map { it.map() }.toMutableList()
        }
    }

    @DgsData(parentType = "Event", field = "images")
    fun imagesForEvent(dfe: DgsDataFetchingEnvironment): CompletableFuture<MutableList<Image>> {
        val ids = dfe.getSource<Event>().images!!.map { it.id }
        return CompletableFuture.supplyAsync {
            getImagesByIdsUseCase.execute(ids).map { it.map() }.toMutableList()
        }
    }

    @DgsData(parentType = "Profile", field = "image")
    fun imageForProfile(dfe: DgsDataFetchingEnvironment): CompletableFuture<Image?> {
        return CompletableFuture.supplyAsync {
            dfe.getSource<Profile>().image?.id?.let { getImagesByIdsUseCase.execute(listOf(it)).lastOrNull()?.map() }
        }
    }
}