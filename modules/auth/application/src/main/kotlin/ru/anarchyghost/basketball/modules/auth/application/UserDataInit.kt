package ru.anarchyghost.basketball.modules.auth.application

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import ru.anarchyghost.basketball.modules.auth.User
import ru.anarchyghost.basketball.modules.auth.application.repository.UserRepository

@Component
internal class UserDataInit(
    private val userRepository: UserRepository
): ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val user = User.create("+78005553535")
        user.assignPermission(User.UserPermission.ADMIN)
        userRepository.save(user)
    }

}