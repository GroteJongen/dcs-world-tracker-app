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
        return createStatisticsDtoFromStatistics(statistics);
    }

    public List<StatisticsDto> addPlayerStatisticsToList(long id) {
        List<Statistics> playerStatistics = statisticsService.getStatisticsByPlayerId(id);
        List<StatisticsDto> playerStatisticsDto = new ArrayList<>();
        for (Statistics statistics : playerStatistics) {
            playerStatisticsDto.add(createStatisticsDtoFromStatistics(statistics));
        }
        return playerStatisticsDto;
    }

    private StatisticsDto createStatisticsDtoFromStatistics(Statistics statistics) {
        long playerId = playerService.getPlayerById(statistics.getPlayerId()).orElseThrow().getId();
        long missionId = missionService.getMissionById(statistics.getMissionId()).orElseThrow().getId();
        return StatisticsDto.builder()
                .playerId(playerId)
                .missionId(missionId)
                .airKills(statistics.getAirKills())
                .groundKills(statistics.getGroundKills())
                .score(statistics.getScore())
                .isWon(statistics.isWon()).build();
    }

}
