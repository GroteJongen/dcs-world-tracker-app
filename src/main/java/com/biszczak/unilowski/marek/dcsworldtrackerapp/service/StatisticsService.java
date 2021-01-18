package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.PlayerHasNoStatisticsOrDoesNotExistsException;
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

    public List<Statistics> getStatisticsByPlayerId(long id) {
        final List<Statistics> playerStats = statisticsRepository.findAllByPlayerId(id);
        if(playerStats.isEmpty()){
            throw new PlayerHasNoStatisticsOrDoesNotExistsException("Statistics for given player does not exist");
        }
        return playerStats;
    }

    public Statistics saveStatisticsForMissionWithName(StatisticsDto statisticsDto) {
        return Statistics.builder().playerId(statisticsDto.getPlayerId())
                .missionId(missionService.findMissionByMissionName(statisticsDto.getMissionName()).getId())
                .airKills(statisticsDto.getAirKills())
                .groundKills(statisticsDto.getGroundKills())
                .score(statisticsDto.getScore())
                .isWon(statisticsDto.isWon()).build();
    }
}
