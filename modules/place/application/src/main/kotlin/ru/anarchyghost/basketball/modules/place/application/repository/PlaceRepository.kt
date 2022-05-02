package ru.anarchyghost.basketball.modules.place.application.repository

import ru.anarchyghost.basketball.modules.place.domain.Place
import java.util.UUID

interface PlaceRepository {
    fun findAllPlaces(): List<Place>
    fun findPlaceById(id: UUID): Place?
    fun findAllPlacesByIds(ids: Collection<UUID>): List<Place>
    fun save(place: Place): Place
    fun deleteById(id: UUID)
}