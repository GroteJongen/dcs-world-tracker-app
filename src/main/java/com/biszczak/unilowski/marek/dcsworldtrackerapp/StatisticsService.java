package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticsService {

    private StatisticsRepository statisticsRepository;
    private MissionService missionService;

    public List<Statistics> getStatistics(){
        return statisticsRepository.findAll();
    }

    public Optional<Statistics> getByID(long id){
        return statisticsRepository.findById(id);
    }

    public List<Statistics> getStatisticsByMissionId(long id){
        return statisticsRepository.findAll().stream().filter(statistic -> statistic.getMissionID() == id).collect(Collectors.toList());
    }

    public List<Statistics> getStatisticsByPlayerId(long id){
        return statisticsRepository.findAll().stream().filter(statistic -> statistic.getPlayerId() == id).collect(Collectors.toList());
    }
}
