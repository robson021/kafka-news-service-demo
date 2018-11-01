package consumer;

import common.config.KafkaProps;
import common.model.News;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@SpringBootApplication
public class ConsumerApp {

    @Bean
    public ConsumerFactory<String, News> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProps.KAFKA_ADDRESS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaProps.DEFAULT_GROUP);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(News.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, News> kafkaListenerContainerFactory(ConsumerFactory<String, News> factory) {
        ConcurrentKafkaListenerContainerFactory<String, News> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        listenerContainerFactory.setConsumerFactory(factory);
        return listenerContainerFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApp.class, args);
    }

}