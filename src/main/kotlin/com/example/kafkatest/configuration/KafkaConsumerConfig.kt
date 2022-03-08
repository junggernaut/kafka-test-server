package com.example.kafkatest.configuration

import com.example.kafkatest.model.Message
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.config.KafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.annotation.EnableKafka


@EnableKafka
@Configuration
class KafkaConsumerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    @Bean
    fun consumerConfigs(): Map<String, Any?> {
        val configurations: MutableMap<String, Any> = HashMap()
        configurations[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        configurations[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configurations[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        configurations[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
        return configurations
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Message> {
        return DefaultKafkaConsumerFactory(consumerConfigs(), StringDeserializer(), JsonDeserializer(Message::class.java))
    }

    @Bean
    fun kafkaListenerContainerFactory(): KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Message>> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Message>()
        factory.consumerFactory = consumerFactory()
        return factory
    }
}