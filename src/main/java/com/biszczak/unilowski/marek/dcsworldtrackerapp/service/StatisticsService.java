package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final MissionService missionService;

    public List<Statistics> getAllStatistics(){
        return statisticsRepository.findAll();
    }
    public void saveStatistics(Statistics[] statistics){
         statisticsRepository.saveAll(Arrays.asList(statistics));
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
