package com.example.kafkatest.configuration

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.kafka.config.TopicBuilder

class KafkaTopicConfig {
    @Bean
    fun topic1(): NewTopic {
        return TopicBuilder.name("reflectoring-1").build()
    }

    @Bean
    fun topic2(): NewTopic {
        return TopicBuilder.name("reflectoring-2").build()
    }
}