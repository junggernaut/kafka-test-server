//package com.example.kafkatest.configuration
//
//import org.springframework.context.annotation.Configuration
//import org.springframework.kafka.annotation.EnableKafka
//import org.springframework.kafka.annotation.KafkaListenerConfigurer
//
//@Configuration
//@EnableKafka
//class KafkaValidatorConfig: KafkaListenerConfigurer {
//
//    /**
//     * reply kafka template
//     * @return KafkaTemplate<String, Member>
//     */
//    fun replyTemplate(): KafkaTemplate<String, Member> {
//        return KafkaTemplate(factory())
//    }
//
//    /**
//     * reply kafka template factory
//     * @return DefaultKafkaProducerFactory<String, Member>
//     */
//    fun factory(): DefaultKafkaProducerFactory<String, Member> {
//        return DefaultKafkaProducerFactory<String, Member>(memberProducerConfig())
//    }
//
//    /**
//     * reply kafka configuration
//     * @return Map<String, Serializable>
//     */
//    private fun memberProducerConfig(): Map<String, Any> =
//        mapOf(
//            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVER,
//            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
//            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java
//        )
//
//}