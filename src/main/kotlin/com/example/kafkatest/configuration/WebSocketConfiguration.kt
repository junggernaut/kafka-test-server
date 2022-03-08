package com.example.kafkatest.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.stereotype.Controller
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@EnableWebSocketMessageBroker
@Configuration
class WebSocketConfiguration : WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/my-chat").setAllowedOriginPatterns("*").withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.setApplicationDestinationPrefixes("/kafka")
        registry.enableSimpleBroker("/topic", "/queue")
    }
}



@Controller
class GreetingController {
    @MessageMapping("/greeting")
    fun handle(greeting : String): String {
        return "[check greeting: $greeting"
    }
}

