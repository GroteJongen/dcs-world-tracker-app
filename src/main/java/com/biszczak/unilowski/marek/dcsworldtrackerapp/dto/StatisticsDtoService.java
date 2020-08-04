package com.biszczak.unilowski.marek.dcsworldtrackerapp.dto;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.MissionService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.PlayerService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsService;
import org.springframework.stereotype.Service;

@Service
public class StatisticsDtoService {

    private StatisticsService statisticsService;
    private MissionService missionService;
    private PlayerService playerService;

    public StatisticsDto createDtoForStatisticsWithId(long id) {
        Statistics statistics = statisticsService.getByID(id).orElseThrow();
        String missionName = missionService.getMissionById(statistics.getMissionID()).orElseThrow().getMissionName();
        String playerName = playerService.getPlayerById(statistics.getPlayerId()).orElseThrow().getName();
        return new StatisticsDto(playerName
                , missionName
                , statistics.getAirKills()
                , statistics.getGroundKills()
                , statistics.getScore()
                , statistics.isWon());
    }
}
