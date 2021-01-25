package com.biszczak.unilowski.marek.dcsworldtrackerapp.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Getter
@Setter
public class KafkaProperties {

    private final String kafkaIp = "127.0.0.1:9092";
    private final Properties properties = new Properties();

    public KafkaProperties() {
        configureKafka();
    }

    public void configureKafka() {
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaIp);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }
}
