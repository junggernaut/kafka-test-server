package com.example.kafkatest.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    var author: String?,
    var content: String?,
    var timestamp: String?,
) {
    override fun toString(): String {
        return "Message{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}'
    }
}
