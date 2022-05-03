package ru.anarchyghost.basketball.modules.api.mutations

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.auth.interactions.CurrentAuthenticatedUserDto
import ru.anarchyghost.basketball.modules.place.interactions.usecase.CreatePlaceUseCase
import ru.anarchyghost.basketball.modules.place.interactions.usecase.RemovePlaceUseCase
import ru.anarchyghost.basketball.modules.place.interactions.usecase.UpdatePlaceUseCase

@DgsComponent
internal class PlaceMutations(
    private val createPlaceUseCase: CreatePlaceUseCase,
    private val updatePlaceUseCase: UpdatePlaceUseCase,
    private val removePlaceUseCase: RemovePlaceUseCase
) {

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun createPlace(
        latitude: Double,
        longitude: Double,
        name: String,
        description: String?,
        address: String?,
        sports: List<String>,
    ) = createPlaceUseCase.execute(
        latitude = latitude,
        longitude = longitude,
        name = name,
        description = description,
        address = address,
        sports = sports,
        createdBy = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId
    ).map()

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun updatePlace(
        id: String,
        name: String,
        description: String?,
        sports: List<String>,
    ) = updatePlaceUseCase.execute(
        id = id,
        name = name,
        description = description,
        sports = sports,
        userId = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString()
    )?.map()

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun removePlace(
        id: String
    ): String {
        removePlaceUseCase.execute(
            id = id,
            userId = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString()
        )
        return id
    }
}