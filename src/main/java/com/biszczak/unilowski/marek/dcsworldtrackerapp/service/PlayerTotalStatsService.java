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
    private final static String STATS_DEATHS = "deaths";
    private final static String STATS_AIR_KILLS = "air kills";
    private final static String STATS_GROUND_KILLS = "ground kills";
    private final static String STATS_SCORE = "score";
    private final static String STATS_LOSES = "loses";
    private final static String STATS_WINS = "wins";
    private final static int STATS_INITIAL_AMOUNT = 1;


    public PlayerTotalStatsDto getTotalStatsOfPlayerWithId(List<Statistics> playerStats) {
        Map<String, Integer> totalStats = countAllStats(playerStats);
        return PlayerTotalStatsDto.builder().killDeathRatio(calculateKillDeathRatio(totalStats))
                .totalDeaths(totalStats.get(STATS_DEATHS))
                .totalGroundKills(totalStats.get(STATS_GROUND_KILLS))
                .totalAirKills(totalStats.get(STATS_AIR_KILLS))
                .totalScore(totalStats.get(STATS_SCORE))
                .totalLoses(totalStats.get(STATS_LOSES))
                .totalVictories(totalStats.get(STATS_WINS))
                .totalMissions(playerStats.size())
                .killDeathRatio(calculateKillDeathRatio(totalStats))
                .winLooseRatio(calculateWinLooseRatio(totalStats)).build();
    }

    private void countWinsAndLoses(List<Statistics> playerStats, Map<String, Integer> totalStats) {
        for (Statistics statistics : playerStats) {
            if (!statistics.isWon()) {
                totalStats.put(STATS_LOSES, totalStats.get(STATS_LOSES) + 1);
            }
            if (statistics.isWon()) {
                totalStats.put(STATS_WINS, totalStats.get(STATS_WINS) + 1);
            }
        }
    }

    private double calculateKillDeathRatio(Map<String, Integer> playerStats) {
        float totalKills = playerStats.get(STATS_AIR_KILLS) + playerStats.get(STATS_GROUND_KILLS);
        float totalDeaths = playerStats.get(STATS_DEATHS);
        return totalKills / totalDeaths;
    }

    private double calculateWinLooseRatio(Map<String, Integer> playerStats) {
        float totalWins = playerStats.get(STATS_WINS);
        float totalLoses = playerStats.get(STATS_LOSES);
        return totalWins / totalLoses;
    }

    private Map<String, Integer> countAllStats(List<Statistics> playerStats) {
        Map<String, Integer> totalStats = prepareMapOfStatistics();
        for (Statistics statistics : playerStats) {
            totalStats.put(STATS_AIR_KILLS, totalStats.get(STATS_AIR_KILLS) + statistics.getAirKills());
            totalStats.put(STATS_GROUND_KILLS, totalStats.get(STATS_GROUND_KILLS) + statistics.getGroundKills());
            totalStats.put(STATS_DEATHS, totalStats.get(STATS_DEATHS) + statistics.getDeaths());
            totalStats.put(STATS_SCORE, totalStats.get(STATS_SCORE) + statistics.getScore());
        }
        countWinsAndLoses(playerStats, totalStats);
        return totalStats;
    }

    private Map<String, Integer> prepareMapOfStatistics() {
        Map<String, Integer> totalStats = new HashMap<>();
        totalStats.put(STATS_AIR_KILLS, STATS_INITIAL_AMOUNT);
        totalStats.put(STATS_GROUND_KILLS, STATS_INITIAL_AMOUNT);
        totalStats.put(STATS_DEATHS, STATS_INITIAL_AMOUNT);
        totalStats.put(STATS_SCORE,STATS_INITIAL_AMOUNT);
        totalStats.put(STATS_WINS, STATS_INITIAL_AMOUNT);
        totalStats.put(STATS_LOSES,STATS_INITIAL_AMOUNT);
        return totalStats;
    }
}
