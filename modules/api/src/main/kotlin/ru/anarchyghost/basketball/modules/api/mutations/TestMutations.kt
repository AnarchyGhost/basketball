package ru.anarchyghost.basketball.modules.api.mutations

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import org.springframework.kafka.core.KafkaTemplate
import ru.anarchyghost.basketball.modules.sending.interactions.events.SendAuthCodeEvent
import ru.anarchyghost.basketball.modules.sending.interactions.usecases.SendAuthCodeUseCase


@DgsComponent
class TestMutations(
    private val sendAuthCodeUseCase: SendAuthCodeUseCase,
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {

    @DgsMutation
    fun test(): String {
//        sendAuthCodeUseCase.execute("","")
        kafkaTemplate.send("sending_send_code", SendAuthCodeEvent("to","from"))
        return "OK"
    }
}