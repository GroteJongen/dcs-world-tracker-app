package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StatisticsDtoService {

    private final StatisticsService statisticsService;
    private final MissionService missionService;
    private final PlayerService playerService;

    public StatisticsDto createDtoForStatisticsWithId(long id) {
        Statistics statistics = statisticsService.getByID(id).orElseThrow();
        String missionName = missionService.getMissionById(statistics.getMissionId()).orElseThrow().getMissionName();
        long playerId = playerService.getPlayerById(statistics.getPlayerId()).orElseThrow().getId();
        return new StatisticsDto(playerId
                , missionName
                , statistics.getAirKills()
                , statistics.getGroundKills()
                , statistics.getScore()
                , statistics.isWon());
    }

    public List<StatisticsDto> createDtoFroAllStatisticsWithPlayerId(long id) {
        List<Statistics> stats = statisticsService.getStatisticsByPlayerId(id);
        List<StatisticsDto> statsDto = new ArrayList<>();
        for (Statistics statistics : stats) {
            statsDto.add(new StatisticsDto(playerService.getPlayerById(statistics.getPlayerId()).get().getId()
                    , missionService.getMissionById(statistics.getMissionId()).get().getMissionName()
                    , statistics.getAirKills(), statistics.getGroundKills()
                    , statistics.getScore()
                    , statistics.isWon()));
        }
        return statsDto;
    }
}
