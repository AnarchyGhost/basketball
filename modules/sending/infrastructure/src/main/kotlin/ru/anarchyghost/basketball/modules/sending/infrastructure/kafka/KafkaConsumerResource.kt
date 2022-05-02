package ru.anarchyghost.basketball.modules.sending.infrastructure.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import ru.anarchyghost.basketball.modules.auth.interactions.events.AuthCodeCreatedEvent
import ru.anarchyghost.basketball.modules.sending.interactions.usecases.SendAuthCodeUseCase

@Component
internal class KafkaConsumerResource(
    private val sendAuthCodeUseCase: SendAuthCodeUseCase
) {
    @KafkaListener(topics = ["\${app.kafka.topics.auth.codeCreated}"], groupId = "sending")
    fun addDocument(
        @Payload event: AuthCodeCreatedEvent,
    ) {
        sendAuthCodeUseCase.execute(to = event.username, code = event.code)
    }
}
