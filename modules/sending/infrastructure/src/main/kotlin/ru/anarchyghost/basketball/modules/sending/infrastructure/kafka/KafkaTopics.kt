package ru.anarchyghost.basketball.modules.sending.infrastructure.kafka

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel

interface KafkaTopics {
    @Input("sending_send_code")
    fun sendCode(): SubscribableChannel

    @Output("sending_code_sent")
    fun codeSent(): MessageChannel
}