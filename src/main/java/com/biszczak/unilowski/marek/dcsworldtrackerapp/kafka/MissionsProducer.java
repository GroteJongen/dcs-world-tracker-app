package com.biszczak.unilowski.marek.dcsworldtrackerapp.kafka;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

@Service
public class MissionsProducer {

    private final KafkaProducer<String, String> missionsProducer = new KafkaProducer<>(new KafkaProperties().getProperties());
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void produceRecord(StatisticsDto statisticsDto) throws JsonProcessingException {
        String kafka_topic = "player_missions_and_stats";
        ProducerRecord<String, String> missionRecord = new ProducerRecord<>(kafka_topic, getJsonStringFromMission(statisticsDto));
        missionsProducer.send(missionRecord);
        missionsProducer.flush();
    }

    private String getJsonStringFromMission(StatisticsDto statisticsDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(statisticsDto);
    }

}
