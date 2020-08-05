package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticsDtoService {

    private final StatisticsService statisticsService;
    private final MissionService missionService;
    private final PlayerService playerService;

    public StatisticsDto createDtoForStatisticsWithId(long id) {
        Statistics statistics = statisticsService.getByID(id).orElseThrow();
        String missionName = missionService.getMissionById(statistics.getMissionId()).orElseThrow().getMissionName();
        String playerName = playerService.getPlayerById(statistics.getPlayerId()).orElseThrow().getName();
        return new StatisticsDto(playerName
                , missionName
                , statistics.getAirKills()
                , statistics.getGroundKills()
                , statistics.getScore()
                , statistics.isWon());
    }
}
