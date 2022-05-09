package ru.anarchyghost.basketball.modules.place.application

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import ru.anarchyghost.basketball.modules.place.application.repository.PlaceRepository
import ru.anarchyghost.basketball.modules.place.domain.Place
import java.util.*

@Component
internal class PlaceDataInit(
    private val placeRepository: PlaceRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val place = Place.create(
            latitude = 55.749385,
            longitude = 37.876975,
            description = "Площадка у дома",
            name = "Площадка у дома",
            createdBy = UUID.fromString("15d1b0a0-4790-414c-968c-475c86845cc7"),
            sports = mutableListOf(Place.SportKind.BASKETBALL),
            address = "У дома"
        )
        placeRepository.save(place)
    }

}