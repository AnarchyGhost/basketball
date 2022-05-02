package ru.anarchyghost.basketball.modules.auth.infrastructure.kafka

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.anarchyghost.basketball.modules.auth.application.EventPublisher
import ru.anarchyghost.basketball.modules.auth.interactions.events.AuthCodeCreatedEvent

@Service
internal class EventPublisherImpl(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) : EventPublisher {
    @Value("\${app.kafka.topics.auth.codeCreated}")
    private lateinit var codeCreatedTopic: String

    override fun publishAuthCodeCreated(username: String, code: String) {
        kafkaTemplate.send(codeCreatedTopic, AuthCodeCreatedEvent(username = username, code = code))
    }
}