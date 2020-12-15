package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;
    private final MissionService missionService;


    public Optional<Statistics> getByID(long id) {
        return statisticsRepository.findById(id);
    }

    public List<Statistics> getStatisticsByMissionId(long id) {
        return statisticsRepository.findAllByMissionId(id);
    }

    public List<Statistics> getStatisticsByPlayerId(long id) {
        return statisticsRepository.findAllByPlayerId(id);
    }

    public Statistics saveStatisticsForMissionWithName(StatisticsDto statisticsDto){
        Statistics statistics = new Statistics();
        statistics.setPlayerId(statisticsDto.getPlayerId());
        statistics.setMissionId(missionService.findMissionByMissionName(statisticsDto.getMissionName()).getId());
        statistics.setAirKills(statisticsDto.getAirKills());
        statistics.setGroundKills(statisticsDto.getGroundKills());
        statistics.setScore(statisticsDto.getScore());
        statistics.setWon(statisticsDto.isWon());
        statisticsRepository.save(statistics);
        return statistics;
    }
}
