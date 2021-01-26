package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.PlayerTotalStatsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PlayerTotalStatsService {


    public PlayerTotalStatsDto getTotalStatsOfPlayerWithId(List<Statistics> playerStats) {
        Map<String, Integer> totalStats = countAllStats(playerStats);
        return PlayerTotalStatsDto.builder().killDeathRatio(calculateKillDeathRatio(totalStats))
                .totalDeaths(totalStats.get("deaths"))
                .totalGroundKills(totalStats.get("air kills"))
                .totalAirKills(totalStats.get("air kills"))
                .totalScore(totalStats.get("score"))
                .totalLoses(totalStats.get("loses"))
                .totalVictories(totalStats.get("wins"))
                .totalMissions(playerStats.size())
                .killDeathRatio(calculateKillDeathRatio(totalStats))
                .winLooseRatio(calculateWinLooseRatio(totalStats)).build();
    }

    private void countWinsAndLoses(List<Statistics> playerStats, Map<String, Integer> totalStats) {
        for (Statistics statistics : playerStats) {
            if (!statistics.isWon()) {
                totalStats.put("loses", totalStats.get("loses") + 1);
            }
            if (statistics.isWon()) {
                totalStats.put("wins", totalStats.get("wins") + 1);
            }
        }
    }

    private double calculateKillDeathRatio(Map<String, Integer> playerStats) {
        float totalKills = playerStats.get("air kills") + playerStats.get("ground kills");
        float totalDeaths = playerStats.get("deaths");
        return totalKills / totalDeaths;
    }

    private double calculateWinLooseRatio(Map<String, Integer> playerStats) {
        float totalWins = playerStats.get("wins");
        float totalLoses = playerStats.get("loses");
        return totalWins / totalLoses;
    }

    private Map<String, Integer> countAllStats(List<Statistics> playerStats) {
        Map<String, Integer> totalStats = prepareMapOfStatistics();
        for (Statistics statistics : playerStats) {
            totalStats.put("air kills", totalStats.get("air kills") + statistics.getAirKills());
            totalStats.put("ground kills", totalStats.get("ground kills") + statistics.getGroundKills());
            totalStats.put("deaths", totalStats.get("deaths") + statistics.getDeaths());
            totalStats.put("score", totalStats.get("score") + statistics.getScore());
        }
        countWinsAndLoses(playerStats, totalStats);
        return totalStats;
    }

    private Map<String, Integer> prepareMapOfStatistics() {
        Map<String, Integer> totalStats = new HashMap<>();
        totalStats.put("air kills", 0);
        totalStats.put("ground kills", 0);
        totalStats.put("deaths", 0);
        totalStats.put("score", 0);
        totalStats.put("wins", 0);
        totalStats.put("loses", 0);
        return totalStats;
    }
}
