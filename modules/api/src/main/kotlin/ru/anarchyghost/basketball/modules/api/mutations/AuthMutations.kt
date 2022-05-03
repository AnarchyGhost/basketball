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
import ru.anarchyghost.basketball.modules.api.loaders.UserDataLoader
import ru.anarchyghost.basketball.modules.api.mappers.map
import ru.anarchyghost.basketball.modules.auth.interactions.AuthenticationDto
import ru.anarchyghost.basketball.modules.auth.interactions.events.AuthCodeRequestedEvent
import ru.anarchyghost.basketball.modules.auth.interactions.AuthenticateByCodeUseCase
import java.util.concurrent.CompletableFuture

@DgsComponent
internal class AuthMutations(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    private val authenticateByCodeUseCase: AuthenticateByCodeUseCase
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

}