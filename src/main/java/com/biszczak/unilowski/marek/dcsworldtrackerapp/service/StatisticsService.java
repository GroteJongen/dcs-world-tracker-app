package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.MissionDoesNotExistException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.PlayerHasNoStatisticsOrDoesNotExistsException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.StatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StatisticsService {

    @Autowired
    private final StatisticsRepository statisticsRepository;
    @Autowired
    private final MissionService missionService;
    @Autowired
    private final PlayerService playerService;

    public Optional<Statistics> getByID(long id) {
        return statisticsRepository.findById(id);
    }

    public List<Statistics> getStatisticsByPlayerId(long id) {
        final List<Statistics> playerStats = statisticsRepository.findAllByPlayerId(id);
        if (playerStats.isEmpty()) {
            throw new PlayerHasNoStatisticsOrDoesNotExistsException("Statistics for given player does not exist");
        }
        return playerStats;
    }

    public Statistics convertStatisticsDtoToStatistics(StatisticsDto statisticsDto) {
        return Statistics.builder().playerId(statisticsDto.getPlayerId())
                .missionId(missionService.getMissionById(statisticsDto.getMissionId()).orElseThrow().getId())
                .airKills(statisticsDto.getAirKills())
                .groundKills(statisticsDto.getGroundKills())
                .score(statisticsDto.getScore())
                .isWon(statisticsDto.isWon()).build();
    }

    public Statistics saveStatisticsForMission(StatisticsDto statisticsDto) {
        Optional<Statistics> statistics = statisticsRepository.findByMissionIdAndPlayerId(statisticsDto.getMissionId(), statisticsDto.getPlayerId());
        if (statistics.isPresent()) {
            Statistics statisticsToUpdate = convertStatisticsDtoToStatistics(statisticsDto);
            statisticsToUpdate.setId(statistics.get().getId());
            return statisticsRepository.save(statisticsToUpdate);
        }
        if (missionService.getMissionById(statisticsDto.getMissionId()).isEmpty()) {
            throw new MissionDoesNotExistException("Mission with given id does not exist");
        }
        if (playerService.getPlayerById(statisticsDto.getPlayerId()).isEmpty()) {
            throw new PlayerHasNoStatisticsOrDoesNotExistsException("There is no player with given Id");
        }
        return statisticsRepository.save(convertStatisticsDtoToStatistics(statisticsDto));

    }
    public List<Statistics> findAllSortedForPlayerId(Sort sort, long userId){
        return statisticsRepository.findAllByPlayerId(userId,sort);
    }

}
