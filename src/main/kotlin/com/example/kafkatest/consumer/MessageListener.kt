package com.example.kafkatest.consumer

import com.example.kafkatest.constans.KafkaConstants
import com.example.kafkatest.model.Message
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component


@Slf4j
@Component
class MessageListener(
    @Autowired
    private var template: SimpMessagingTemplate
) {
    @KafkaListener(topics = [KafkaConstants.KAFKA_TOPIC], groupId = KafkaConstants.GROUP_ID)
    fun listen(message: Message?) {
        template.convertAndSend("/topic/group", message)
    }
}