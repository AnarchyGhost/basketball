package ru.anarchyghost.basketball.modules.api.loaders

import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.MappedBatchLoader
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.review.interactions.usecase.GetAllReviewsForPlacesUseCase
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader(name = "averageRate")
internal class AverageRateDataLoader(
    private val getAllReviewsForPlacesUseCase: GetAllReviewsForPlacesUseCase
) : MappedBatchLoader<String, Int> {

    override fun load(keys: Set<String>): CompletionStage<Map<String, Int>> {
        val ids = keys.toList()
        return CompletableFuture.supplyAsync {
            getAllReviewsForPlacesUseCase.execute(ids).map { it.map() }.groupBy { it.placeId }.toList()
                .associate { it.first to (it.second.sumOf { rev -> rev.rate } / it.second.size) }
        }
    }
}