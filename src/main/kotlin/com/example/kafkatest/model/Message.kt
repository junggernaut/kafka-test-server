package com.example.kafkatest.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Message(
    @JsonProperty("author")
    var author: String?,
    @JsonProperty("content")
    var content: String?,
    @JsonProperty("timestamp")
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
