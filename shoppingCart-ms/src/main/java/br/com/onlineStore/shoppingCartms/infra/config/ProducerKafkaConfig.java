package br.com.onlineStore.shoppingCartms.infra.config;

import br.com.onlineStore.shoppingCartms.application.dto.ShoppingCartDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;

@Configuration
public class ProducerKafkaConfig {
    @Value("${spring.kafka.bootstrap-servers:localhost:29092}")
    private String bootstrapAddress;

    @Bean
    public ProducerFactory<String, ShoppingCartDto> producerFactory(){
        var props = new HashMap<String, Object>();
        props.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(props);
    }
    @Bean
    public KafkaTemplate<String, ShoppingCartDto> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
