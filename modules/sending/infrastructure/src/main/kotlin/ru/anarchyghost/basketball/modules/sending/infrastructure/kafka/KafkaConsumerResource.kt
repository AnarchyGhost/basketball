package ru.anarchyghost.basketball.modules.sending.infrastructure.kafka

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import ru.anarchyghost.basketball.modules.sending.interactions.events.AuthCodeSentEvent
import ru.anarchyghost.basketball.modules.sending.interactions.events.SendAuthCodeEvent

@Component
internal class KafkaConsumerResource {

    @KafkaListener(topics = ["sending_send_code"], groupId = "sending")
    fun addDocument(
        @Payload doc: SendAuthCodeEvent,
    ) {
        println(doc.to+"3")
    }

    @KafkaListener(topics = ["sending_code_sent"], groupId = "sending")
    fun addDoc(
        @Payload doc: AuthCodeSentEvent,
    ) {
        println(doc.to+"3")
    }
}
