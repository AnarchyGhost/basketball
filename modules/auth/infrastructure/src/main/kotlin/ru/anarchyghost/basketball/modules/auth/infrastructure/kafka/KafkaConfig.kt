package ru.anarchyghost.basketball.modules.auth.infrastructure.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer


@Configuration
@EnableKafka
internal class KafkaConfig {

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
    fun producerConfigs(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] =  server
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = JsonSerializer::class.java
        props[ProducerConfig.CLIENT_ID_CONFIG] = "a"
        return props
    }

    @Bean
    fun producerStarshipFactory(): ProducerFactory<String, Any> {
        return DefaultKafkaProducerFactory(producerConfigs())
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any> {
        val template: KafkaTemplate<String, Any> = KafkaTemplate(producerStarshipFactory())
        template.messageConverter = StringJsonMessageConverter()
        return template
    }
    @Bean
    fun converter(): StringJsonMessageConverter? {
        return StringJsonMessageConverter()
    }
}