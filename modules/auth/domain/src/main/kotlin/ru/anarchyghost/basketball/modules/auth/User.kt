package ru.anarchyghost.basketball.modules.auth

import java.util.UUID

data class User(
    val id: UUID,
    val phoneNumber: String,
    val permissions: MutableList<UserPermission>,
    var profileId: UUID?
) {
    companion object {
        fun create(
            phoneNumber: String,
        ) = User(
            phoneNumber = phoneNumber,
            id = UUID.randomUUID(),
            permissions = mutableListOf(),
            profileId = null
        )
    }

    fun assignPermission(
        permission: UserPermission
    ) {
        check(!permissions.contains(permission)) {"User $id already have $permission"}
        this.permissions.add(permission)
    }

    fun removePermission(
        permission: UserPermission
    ) {
        this.permissions.remove(permission)
    }

    fun assignProfile(
        profileId: UUID
    ) {
        check(this.profileId == null) {"Profile already exists"}
        this.profileId = profileId
    }

    enum class UserPermission {
        ADMIN, MODER, MANAGER, PLACE_OWNER
    }
}