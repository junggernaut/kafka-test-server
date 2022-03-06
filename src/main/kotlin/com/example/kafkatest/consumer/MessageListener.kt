package com.example.kafkatest.consumer

import com.example.kafkatest.model.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component


@Component
class MessageListener(
    private var template: SimpMessagingTemplate
) {

    @KafkaListener(topics = ["kafka-chat"], groupId = "foo")
    fun listen(message: Message) {
        try {
            template.convertAndSend("/topic/group", message)
        } catch (e: Exception) {
        throw RuntimeException(e)
    }
    }
}