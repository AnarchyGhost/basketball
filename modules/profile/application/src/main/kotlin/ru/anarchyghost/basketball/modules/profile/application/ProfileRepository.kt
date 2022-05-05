package ru.anarchyghost.basketball.modules.profile.application

import ru.anarchyghost.basketball.modules.profile.domain.Profile
import java.util.UUID

interface ProfileRepository {
    fun findProfileById(id: UUID): Profile?
    fun save(profile: Profile): Profile
}