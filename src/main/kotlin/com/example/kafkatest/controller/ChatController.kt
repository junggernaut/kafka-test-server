package com.example.kafkatest.controller

import com.example.kafkatest.model.Message
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime


@CrossOrigin
@RestController
@RequestMapping(value = ["/kafka"])
class ChatController(
    private var kafkaTemplate: KafkaTemplate<String, Message>
) {

    @MessageMapping("/{chatRoomId}")
    fun getMessage(@RequestBody message: Message, @DestinationVariable chatRoomId: String) {
        println(message)
        message.timestamp = LocalDateTime.now().toString()
        try {
            kafkaTemplate.send(chatRoomId, message).get()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

//    @MessageMapping("/queue/test")
//    @SendTo("/topic/group")
//    fun broadcastGroupMessage(@Payload message: Message): Message {
//        return message
//    }

}