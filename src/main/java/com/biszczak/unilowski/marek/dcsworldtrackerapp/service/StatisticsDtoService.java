package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.FilterCriteriaDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StatisticsDtoService {

    @Autowired
    private final StatisticsService statisticsService;
    @Autowired
    private final MissionService missionService;
    @Autowired
    private final PlayerService playerService;


    public StatisticsDto createDtoForStatisticsWithId(long id) {
        Statistics statistics = statisticsService.getByID(id).orElseThrow();
        return createStatisticsDtoFromStatistics(statistics);
    }

    public List<StatisticsDto> getAllPlayerMissionStats(long id) {
        List<Statistics> playerStatistics = statisticsService.getStatisticsByPlayerId(id);
        List<StatisticsDto> playerStatisticsDto = new ArrayList<>();
        for (Statistics statistics : playerStatistics) {
            playerStatisticsDto.add(createStatisticsDtoFromStatistics(statistics));
        }
        return playerStatisticsDto;
    }

    public List<StatisticsDto> getAllSortedResults(long id, FilterCriteriaDto filterCriteriaDto) {
        List<StatisticsDto> sortedStatistics = new ArrayList<>();
        if (filterCriteriaDto.getSort().equals("ASC")) {
            List<Statistics> ascSortedStats = statisticsService.findAllSortedForPlayerId(Sort.by(Sort.Direction.ASC, filterCriteriaDto.getParamName()), id);
            for (Statistics statistics : ascSortedStats) {
                sortedStatistics.add(createStatisticsDtoFromStatistics(statistics));
            }
            return sortedStatistics;
        }
        if (filterCriteriaDto.getSort().equals("DESC")) {
            List<Statistics> ascSortedStats = statisticsService.findAllSortedForPlayerId(Sort.by(Sort.Direction.DESC, filterCriteriaDto.getParamName()), id);
            for (Statistics statistics : ascSortedStats) {
                sortedStatistics.add(createStatisticsDtoFromStatistics(statistics));
            }
            return sortedStatistics;
        } else {
            return getAllPlayerMissionStats(id);
        }
    }

    private StatisticsDto createStatisticsDtoFromStatistics(Statistics statistics) {
        long playerId = playerService.getPlayerById(statistics.getPlayerId()).orElseThrow().getId();
        String missionName = missionService.getMissionById(statistics.getMissionId()).orElseThrow().getMissionName();
        return StatisticsDto.builder()
                .playerId(playerId)
                .missionName(missionName)
                .airKills(statistics.getAirKills())
                .groundKills(statistics.getGroundKills())
                .score(statistics.getScore())
                .isWon(statistics.isWon()).build();
    }

}
