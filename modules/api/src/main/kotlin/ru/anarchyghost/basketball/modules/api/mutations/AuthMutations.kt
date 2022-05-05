package ru.anarchyghost.basketball.modules.api.mutations

import com.netflix.dgs.codgen.generated.types.User
import com.netflix.dgs.codgen.generated.types.UserAuthenticationPayload
import com.netflix.dgs.codgen.generated.types.UserAuthenticationResult
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import ru.anarchyghost.basketball.modules.api.loaders.UserDataLoader
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.auth.interactions.*
import ru.anarchyghost.basketball.modules.auth.interactions.events.AuthCodeRequestedEvent
import ru.anarchyghost.basketball.modules.images.interactions.usecase.SaveImageUseCase
import ru.anarchyghost.basketball.modules.profile.interactions.CreateProfileUseCase
import ru.anarchyghost.basketball.modules.profile.interactions.UpdateProfileUseCase
import java.util.*
import java.util.concurrent.CompletableFuture

@DgsComponent
internal class AuthMutations(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    private val authenticateByCodeUseCase: AuthenticateByCodeUseCase,
    private val removeRoleFromUserUseCase: RemoveRoleFromUserUseCase,
    private val assignRoleToUserUseCase: AssignRoleToUserUseCase,
    private val createProfileUseCase: CreateProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val saveImageUseCase: SaveImageUseCase,
    private val assignProfileToUserUseCase: AssignProfileToUserUseCase
) {
    @Value("\${app.kafka.topics.auth.codeRequested}")
    private lateinit var codeRequestedTopic: String

    @DgsMutation
    fun requestAuthCode(username: String): String {
        println(username)
        kafkaTemplate.send(codeRequestedTopic, AuthCodeRequestedEvent(username))
        return username
    }

    @DgsData(parentType = "UserAuthenticationPayload", field = "user")
    fun authenticatedUser(dfe: DgsDataFetchingEnvironment): CompletableFuture<User> {
        val id = dfe.getSource<UserAuthenticationPayload>().user.id
        val dataLoader = dfe.getDataLoader<String, User>(UserDataLoader::class.java)
        return dataLoader.load(id)
    }


    @DgsMutation
    fun authWithCode(username: String, code: String): UserAuthenticationResult {
        return handleAuthenticationResult(
            authenticateByCodeUseCase.execute(username, code)
        )
    }

    private fun handleAuthenticationResult(authenticationDto: AuthenticationDto): UserAuthenticationResult {
        authenticationDto.error?.let {
            return it.map()
        }
        return authenticationDto.map()
    }

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun givePermissionsToUser(
    userId: String,
    permission: String
    ): String {
        assignRoleToUserUseCase.execute(
            userId = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId,
            assignRole = UserPermissionDto.valueOf(permission),
            assignTo = UUID.fromString(userId)
        )
        return userId
    }

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun removePermissionsFromUser(
    userId: String,
    permission: String
    ): String {
        removeRoleFromUserUseCase.execute(
            userId = (SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto).userId,
            removeRole = UserPermissionDto.valueOf(permission),
            removeFrom = UUID.fromString(userId)
        )
        return userId
    }

    @PreAuthorize("isAuthenticated()")
    @DgsMutation
    fun saveUserProfile(
        image: String?,
        email: String?,
        username: String,
    ): String {
        val currentUser = SecurityContextHolder.getContext().authentication.principal as CurrentAuthenticatedUserDto
        val imgId = image?.let { saveImageUseCase.execute(image, currentUser.userId.toString()) }
        return if(currentUser.profileId == null) {
            val profile = createProfileUseCase.execute(
                username = username,
                email = email,
                imageId = imgId?.id
            )
            assignProfileToUserUseCase.execute(userId = currentUser.userId, profileId = UUID.fromString(profile.id))
            profile.id
        } else {
            updateProfileUseCase.execute(
                profileId = currentUser.profileId!!.toString(),
                username = username,
                email = email,
                imageId = imgId?.id
            )
            currentUser.profileId.toString()
        }
    }

}