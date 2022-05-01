package ru.anarchyghost.basketball.modules.sending

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.anarchyghost.basketball.modules.sending.infrastructure.kafka.KafkaTopics

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableBinding(KafkaTopics::class)
internal class SenderApplication

@Configuration
internal class KafkaConfig {
    @Bean
    fun getObectMapper(): ObjectMapper = ObjectMapper().registerKotlinModule()
}

fun main(args: Array<String>) {
    SpringApplication.run(SenderApplication::class.java, *args)
}