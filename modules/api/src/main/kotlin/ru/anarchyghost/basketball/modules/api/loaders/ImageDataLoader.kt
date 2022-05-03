package ru.anarchyghost.basketball.modules.api.loaders

import com.netflix.dgs.codgen.generated.types.Image
import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.BatchLoader
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.images.interactions.usecase.GetImagesByIdsUseCase
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@DgsDataLoader(name = "reviews")
internal class ImageDataLoader(
    private val getImagesByIdsUseCase: GetImagesByIdsUseCase
) : BatchLoader<String, Image> {

    override fun load(keys: MutableList<String>): CompletionStage<MutableList<Image>>? {
        return CompletableFuture.supplyAsync {
            getImagesByIdsUseCase.execute(keys).map { it.map() }.toMutableList()
        }
    }
}
