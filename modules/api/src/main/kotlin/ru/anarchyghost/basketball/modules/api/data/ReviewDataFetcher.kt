package ru.anarchyghost.basketball.modules.api.data

import com.netflix.dgs.codgen.generated.types.Place
import com.netflix.dgs.codgen.generated.types.Review
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import ru.anarchyghost.basketball.modules.api.loaders.ReviewDataLoader
import java.util.concurrent.CompletableFuture

@DgsComponent
internal class ReviewDataFetcher {
    @DgsData(parentType = "Place", field = "reviews")
    fun reviewsForPlace(dfe: DgsDataFetchingEnvironment): CompletableFuture<List<Review>> {
        val id = dfe.getSource<Place>().id
        val dataLoader = dfe.getDataLoader<String, List<Review>>(ReviewDataLoader::class.java)
        return dataLoader.load(id)
    }
}