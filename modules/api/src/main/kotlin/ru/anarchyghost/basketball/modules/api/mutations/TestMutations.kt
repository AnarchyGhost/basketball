package ru.anarchyghost.basketball.modules.api.mutations

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import ru.anarchyghost.basketball.modules.sending.interactions.events.AuthCodeSentEvent
import ru.anarchyghost.basketball.modules.sending.interactions.events.SendAuthCodeEvent
import ru.anarchyghost.basketball.modules.sending.interactions.usecases.SendAuthCodeUseCase


@DgsComponent
class TestMutations(
    private val sendAuthCodeUseCase: SendAuthCodeUseCase,
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    @Value("\${app.kafka.topics.sending.sendCode}")
    private lateinit var sendCodeTopic: String
    @Value("\${app.kafka.topics.sending.codeSent}")
    private lateinit var codeSentTopic: String

    @DgsMutation
    fun test(): String {
//        sendAuthCodeUseCase.execute("","")
        kafkaTemplate.send(sendCodeTopic, SendAuthCodeEvent("to","from"))
        kafkaTemplate.send(codeSentTopic, AuthCodeSentEvent("to"))
        return "OK"
    }
}