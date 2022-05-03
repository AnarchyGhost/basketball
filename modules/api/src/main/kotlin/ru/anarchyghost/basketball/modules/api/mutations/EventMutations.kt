package ru.anarchyghost.basketball.modules.api.mutations

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.auth.interactions.CurrentAuthenticatedUserDto
import ru.anarchyghost.basketball.modules.event.interactions.usecase.CreateEventUseCase
import ru.anarchyghost.basketball.modules.event.interactions.usecase.RemoveEventUseCase
import ru.anarchyghost.basketball.modules.event.interactions.usecase.UpdateEventUseCase
import ru.anarchyghost.basketball.modules.place.interactions.usecase.GetPlaceByIdUseCase

@DgsComponent
internal class EventMutations(
    private val createEventUseCase: CreateEventUseCase,
    private val updateEventUseCase: UpdateEventUseCase,
    private val removeEventUseCase: RemoveEventUseCase,
    private val getPlaceByIdUseCase: GetPlaceByIdUseCase
) {

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun createEvent(
        latitude: Double?,
        longitude: Double?,
        name: String,
        description: String?,
        address: String?,
        sports: List<String>,
        startDateTime: String,
        endDateTime: String,
        placeId: String?
    ) = placeId?.let {
        getPlaceByIdUseCase.execute(it)?.let { place ->
            createEventUseCase.execute(
                latitude = place.latitude,
                longitude = place.longitude,
                name = name,
                description = description,
                address = place.address,
                sports = sports,
                createdBy = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString(),
                placeId = it,
                startDateTime = startDateTime.toLong(),
                endDateTime = endDateTime.toLong()
            ).map()
        }
    } ?: createEventUseCase.execute(
        latitude = latitude!!,
        longitude = longitude!!,
        name = name,
        description = description,
        address = address,
        sports = sports,
        createdBy = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString(),
        placeId = null,
        startDateTime = startDateTime.toLong(),
        endDateTime = endDateTime.toLong()
    ).map()

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun updateEvent(
        id: String,
        name: String,
        description: String?,
        sports: List<String>,
        latitude: Double?,
        longitude: Double?,
        placeId: String?,
        address: String?,
        startDateTime: String,
        endDateTime: String,
    ) = (placeId?.let {
        getPlaceByIdUseCase.execute(it)?.let { place ->
            updateEventUseCase.execute(
                id = id,
                latitude = place.latitude,
                longitude = place.longitude,
                name = name,
                description = description,
                address = place.address,
                sports = sports,
                userId = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString(),
                placeId = it,
                startDateTime = startDateTime.toLong(),
                endDateTime = endDateTime.toLong()
            )
        }
    } ?: updateEventUseCase.execute(
        latitude = latitude!!,
        longitude = longitude!!,
        name = name,
        description = description,
        address = address,
        sports = sports,
        userId = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString(),
        placeId = null,
        startDateTime = startDateTime.toLong(),
        endDateTime = endDateTime.toLong(),
        id = id
    ))?.map()

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun removeEvent(
        id: String
    ): String {
        removeEventUseCase.execute(
            id = id,
            userId = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId.toString()
        )
        return id
    }
}