package ru.anarchyghost.basketball.modules.auth.application.usecase

import org.springframework.stereotype.Service
import ru.anarchyghost.basketball.modules.auth.Code
import ru.anarchyghost.basketball.modules.auth.CodeGenerator
import ru.anarchyghost.basketball.modules.auth.application.CodeExpirationSpecification
import ru.anarchyghost.basketball.modules.auth.application.repository.CodeRepository
import ru.anarchyghost.basketball.modules.auth.interactions.CreateAuthenticationCodeUseCase

@Service
class CreateAuthenticationCodeUseCaseImpl(
    private val codeRepository: CodeRepository,
    private val codeGenerator: CodeGenerator,
) : CreateAuthenticationCodeUseCase {

    companion object {
        val EmailRegex = Regex("^\\S+@\\S+\\.\\S{2,}$")
        val PhoneRegex = Regex("(^\\+?[87]\\d{10}\$)|(^[9]\\d{9}\$)|(^\\+7 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2})")
    }

    override fun execute(username: String) {
        require(PhoneRegex.matches(username)){
            "Provided username is not a valid Phone number"
        }
        val code = codeRepository.findByUsername(username)
            ?: Code.create(username, codeGenerator)

        if (CodeExpirationSpecification.create().isExpired(code)) {
            code.renew(codeGenerator)
        }
        codeRepository.save(code)
        //TODO: remove print
        println(code.value)
        //eventPublisher.publish(AuthenticationCodeCreated(username, code.value))
    }
}