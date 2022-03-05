package com.example.kafkatest.controller

import com.example.kafkatest.constans.KafkaConstants
import com.example.kafkatest.model.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime



@CrossOrigin
@RestController
@RequestMapping(value = ["/kafka"])
class ChatController(
    private var kafkaTemplate: KafkaTemplate<String, Message>
) {
    @Value("\${spring.kafka.producer.kafka-topic}")
    lateinit var kafkaTopic: String

    @PostMapping(value = ["/publish"])
    fun sendMessage(@RequestBody message: Message) {
        println(message)
        message.timestamp = LocalDateTime.now().toString()
        try {
            kafkaTemplate.send(kafkaTopic, message).get()
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