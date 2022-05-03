package ru.anarchyghost.basketball.modules.api.loaders

import com.netflix.dgs.codgen.generated.types.Review
import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.MappedBatchLoader
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.review.interactions.usecase.GetAllReviewsForPlacesUseCase
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader(name = "reviews")
internal class ReviewDataLoader(
    private val getAllReviewsForPlacesUseCase: GetAllReviewsForPlacesUseCase
): MappedBatchLoader<String, List<Review>> {

    override fun load(keys: Set<String>): CompletionStage<Map<String, List<Review>>> {
        val ids = keys.toList()
        return CompletableFuture.supplyAsync {
            getAllReviewsForPlacesUseCase.execute(ids).map{it.map()}.groupBy { it.placeId }
        }
    }
}
