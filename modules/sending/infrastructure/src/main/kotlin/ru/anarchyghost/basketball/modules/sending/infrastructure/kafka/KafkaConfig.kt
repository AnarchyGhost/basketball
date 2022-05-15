package ru.anarchyghost.basketball.modules.sending.infrastructure.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.serializer.JsonDeserializer


@Configuration
@EnableKafka
internal class KafkaConfigSecond {
    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var server: String
    @Bean
    fun consumerConfigs(): Map<String, Any> {
        val config: MutableMap<String, Any> = mutableMapOf()
        config[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = server
        config[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        config[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        config[ConsumerConfig.GROUP_ID_CONFIG] = "1"
        return config
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> {
        return DefaultKafkaConsumerFactory(consumerConfigs())
    }

    @Bean
    fun singleFactory(): KafkaListenerContainerFactory<*>? {
        val factory: ConcurrentKafkaListenerContainerFactory<String, Any> =
            ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.consumerFactory = consumerFactory()
        factory.isBatchListener = false
        factory.setMessageConverter(StringJsonMessageConverter())
        return factory
    }

    @Bean
    fun converter(): StringJsonMessageConverter? {
        return StringJsonMessageConverter()
    }

}