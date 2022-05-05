package ru.anarchyghost.basketball.modules.profile.domain

import java.util.*

data class Profile(
    var id: UUID, var username: String, var email: String?, var imageId: UUID?
) {
    companion object {
        val emailRegex = Regex("^\\S+@\\S+\\.\\S{2,}$")

        fun create(
            username: String, email: String?, imageId: UUID?
        ): Profile {
            email?.let { check(emailRegex.matches(it)) }
            return Profile(
                id = UUID.randomUUID(), username = username, email = email, imageId = imageId
            )
        }
    }


    fun update(username: String, email: String?, imageId: UUID?) {
        email?.let { check(emailRegex.matches(it)) }
        this.username = username
        this.email = email
        this.imageId = imageId
    }
}