package ru.anarchyghost.basketball.modules.auth.infrastructure.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import ru.anarchyghost.basketball.modules.auth.interactions.CreateAuthenticationCodeUseCase
import ru.anarchyghost.basketball.modules.auth.interactions.events.AuthCodeRequestedEvent
@Component
internal class EventListener(
    private val createAuthenticationCodeUseCase: CreateAuthenticationCodeUseCase
) {

    @KafkaListener(topics = ["\${app.kafka.topics.auth.codeRequested}"], groupId = "auth")
    fun addDocument(
        @Payload event: AuthCodeRequestedEvent,
    ) {
        println("QQ")
        createAuthenticationCodeUseCase.execute(event.username)
    }

}
