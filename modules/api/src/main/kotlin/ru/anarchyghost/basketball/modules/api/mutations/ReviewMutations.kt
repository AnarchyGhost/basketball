package ru.anarchyghost.basketball.modules.api.mutations

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.auth.interactions.CurrentAuthenticatedUserDto
import ru.anarchyghost.basketball.modules.review.interactions.usecase.CreateReviewUseCase
import ru.anarchyghost.basketball.modules.review.interactions.usecase.RemoveReviewUseCase
import ru.anarchyghost.basketball.modules.review.interactions.usecase.UpdateReviewUseCase

@DgsComponent
internal class ReviewMutations(
    private val createReviewUseCase: CreateReviewUseCase,
    private val updateReviewUseCase: UpdateReviewUseCase,
    private val removeReviewUseCase: RemoveReviewUseCase
) {

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun createReview(
        placeId: String,
        text: String?,
        rate: Int,
    ) = createReviewUseCase.execute(
        placeId = placeId,
        text = text,
        rate = rate,
        createdBy = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString()
    ).map()

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun updateReview(
        id: String,
        text: String?,
        rate: Int,
    ) = updateReviewUseCase.execute(
        id = id,
        text = text,
        rate = rate,
        userId = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString()
    )?.map()

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun removeReview(
        id: String
    ) = removeReviewUseCase.execute(
        id = id,
        userId = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString()
    )
}