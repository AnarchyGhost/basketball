package ru.anarchyghost.basketball.modules.auth.application

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import ru.anarchyghost.basketball.modules.auth.Token
import ru.anarchyghost.basketball.modules.auth.User
import ru.anarchyghost.basketball.modules.auth.application.repository.TokenRepository
import ru.anarchyghost.basketball.modules.auth.application.repository.UserRepository
import java.util.*

@Component
internal class UserDataInit(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val user = User(
            id = UUID.fromString("15d1b0a0-4790-414c-968c-475c86845cc7"),
            phoneNumber = "+78005553535",
            profileId = null,
            permissions = mutableListOf()
        )
        user.assignPermission(User.UserPermission.ADMIN)
        userRepository.save(user)
        val token = Token.create(
            userId = user.id,
            tokenValue = "test"
        )
        tokenRepository.save(token)
    }

}