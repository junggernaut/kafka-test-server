package com.example.kafkatest.controller

import com.example.kafkatest.constans.KafkaConstants
import com.example.kafkatest.model.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@CrossOrigin
@RestController
@RequestMapping(value = ["/kafka"])
class ChatController {
    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, Message>
    @PostMapping(value = ["/publish"])
    fun sendMessage(@RequestBody message: Message) {
        message.timestamp = LocalDateTime.now().toString()
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    fun broadcastGroupMessage(@Payload message: Message): Message {
        return message
    }
}