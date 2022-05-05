package ru.anarchyghost.basketball.modules.auth

import java.util.UUID

data class User(
    val id: UUID,
    val phoneNumber: String,
    val permissions: MutableList<UserPermission>
) {
    companion object {
        fun create(
            phoneNumber: String,
        ) = User(
            phoneNumber = phoneNumber,
            id = UUID.randomUUID(),
            permissions = mutableListOf()
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

    enum class UserPermission {
        ADMIN, MODER, MANAGER, PLACE_OWNER
    }
}