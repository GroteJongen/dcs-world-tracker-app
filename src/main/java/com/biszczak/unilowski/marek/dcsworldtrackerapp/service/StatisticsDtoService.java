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
        playerStatistics.forEach(x -> playerStatisticsDto.add(createStatisticsDtoFromStatistics(x)));
        return playerStatisticsDto;
    }


    public List<StatisticsDto> getAllSortedResults(long id, FilterCriteriaDto filterCriteriaDto) {
        if (filterCriteriaDto.getSort().equals("ASC")) {
            List<Statistics> ascSortedStats = statisticsService.findAllSortedForPlayerId(Sort.by(Sort.Direction.ASC,
                    filterCriteriaDto.getParamName()), id);
            return addStatisticsDtoToList(ascSortedStats);
        }
        if (filterCriteriaDto.getSort().equals("DESC")) {
            List<Statistics> descSortedStatistics = statisticsService.findAllSortedForPlayerId(
                    Sort.by(Sort.Direction.DESC, filterCriteriaDto.getParamName()), id);
            return addStatisticsDtoToList(descSortedStatistics);
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
                .deaths(statistics.getDeaths())
                .isWon(statistics.isWon()).build();
    }

    private List<StatisticsDto> addStatisticsDtoToList(List<Statistics> statistics) {
        List<StatisticsDto> convertedStats = new ArrayList<>();
        statistics.forEach(x -> convertedStats.add(createStatisticsDtoFromStatistics(x)));
        return convertedStats;
    }

}
