package com.biszczak.unilowski.marek.dcsworldtrackerapp.kafka;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class MissionsProducer {


    private KafkaProducer<String, String> missionsProducer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MissionsProducer() throws IOException {
        if (loadPropertiesFromFile().getProperty("enable-kafka").equals("false")) {
            return;
        }
        this.missionsProducer = new KafkaProducer<>(new KafkaProperties(loadPropertiesFromFile().getProperty("kafka-server.url")).getProperties());
    }

    public void produceRecord(StatisticsDto statisticsDto) throws IOException {
        if (loadPropertiesFromFile().getProperty("enable-kafka").equals("false")) {
            return;
        }
        String kafka_topic = "player_missions_and_stats";
        ProducerRecord<String, String> missionRecord = new ProducerRecord<>(kafka_topic,getJsonStringFromMission(statisticsDto));
        missionsProducer.send(missionRecord);
        missionsProducer.flush();
    }

    private String getJsonStringFromMission(StatisticsDto statisticsDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(statisticsDto);
    }

    private Properties loadPropertiesFromFile() throws IOException {
        Properties kafkaProperties = new Properties();
        String appConfigPath = "src/main/resources/kafka.properties.properties";
        kafkaProperties.load(new FileInputStream(appConfigPath));
        return kafkaProperties;
    }

}
