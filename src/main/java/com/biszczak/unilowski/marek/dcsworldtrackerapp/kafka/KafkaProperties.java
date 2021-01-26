package com.biszczak.unilowski.marek.dcsworldtrackerapp.kafka;


import lombok.Getter;

import lombok.Setter;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Getter
@Setter
public class KafkaProperties {

    private final String kafkaIp;
    private final Properties properties = new Properties();

    public KafkaProperties(String kafkaIp) {
        this.kafkaIp = kafkaIp;
        configureKafka();
    }

    public void configureKafka() {
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaIp);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }
}
