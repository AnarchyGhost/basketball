package ru.anarchyghost.basketball.modules.api.mutations

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import ru.anarchyghost.basketball.modules.auth.interactions.CurrentAuthenticatedUserDto
import ru.anarchyghost.basketball.modules.images.interactions.dto.ImageDto
import ru.anarchyghost.basketball.modules.images.interactions.usecase.DeleteImageUseCase
import ru.anarchyghost.basketball.modules.images.interactions.usecase.SaveImageUseCase
import ru.anarchyghost.basketball.modules.place.interactions.usecase.AssignImageToPlaceUseCase
import ru.anarchyghost.basketball.modules.place.interactions.usecase.RemoveImageFromPlaceUseCase

@DgsComponent
internal class ImageMutations(
    private val assignImageToPlaceUseCase: AssignImageToPlaceUseCase,
    private val saveImageUseCase: SaveImageUseCase,
    private val removeImageFromPlaceUseCase: RemoveImageFromPlaceUseCase,
    private val deleteImageUseCase: DeleteImageUseCase
) {

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun loadImageToPlace(
        placeId: String,
        image: String,
    ): ImageDto {
        val usr =
            (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString()
        val img = saveImageUseCase.execute(
            userId = usr,
            image = image
        )
        assignImageToPlaceUseCase.execute(
            placeId = placeId,
            imageId = img.id,
            userId = usr
        )
        return img
    }

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun removeImageFromPlace(
        placeId: String,
        imageId: String,
    ): String {
        val usr =
            (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString()
        deleteImageUseCase.execute(
            id = imageId,
            userId = usr
        )
        removeImageFromPlaceUseCase.execute(
            placeId = placeId,
            imageId = imageId,
            userId = usr
        )
        return imageId
    }
}