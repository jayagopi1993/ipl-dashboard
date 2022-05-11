package com.rm.ipldashboard.kafka;

import com.rm.ipldashboard.entity.ApplicationAccessLog;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, ApplicationAccessLog> consumerFactory() {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        configuration.put(ConsumerConfig.GROUP_ID_CONFIG, "log-message");
        configuration.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configuration.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaConsumerFactory<String, ApplicationAccessLog>(configuration, new StringDeserializer(),new JsonDeserializer<>(ApplicationAccessLog.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ApplicationAccessLog> concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ApplicationAccessLog> factory = new ConcurrentKafkaListenerContainerFactory<String, ApplicationAccessLog>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
