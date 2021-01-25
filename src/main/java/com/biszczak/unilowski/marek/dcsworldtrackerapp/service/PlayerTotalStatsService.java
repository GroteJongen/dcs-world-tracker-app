package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.PlayerStats;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlayerTotalStatsService {

    private final StatisticsService statisticsService;

    public PlayerStats getTotalStatsOfPlayerWithId(long id) {
        List<Statistics> playerStats = statisticsService.getStatisticsByPlayerId(id);
        int totalGroundKills = calculateTotalGroundKillsByPlayerId(playerStats);
        int totalAirKills = calcTotalAirKillsByPlayerId(playerStats);
        int totalScore = calcTotalScoreByPlayerId(playerStats);
        int totalWins = countAllWinsByPlayerId(playerStats);
        int totalLoses = countAllLosesByPlayerId(playerStats);
        int totalGames = statisticsService.getStatisticsByPlayerId(id).size();
        return new PlayerStats(totalGames, totalAirKills, totalGroundKills, totalScore, totalWins, totalLoses);
    }


    private int calculateTotalGroundKillsByPlayerId(List<Statistics> playerStats) {
        int sum = 0;
        for (Statistics statistics : playerStats) {
            sum += statistics.getGroundKills();
        }
        return sum;
    }

    private int calcTotalAirKillsByPlayerId(List<Statistics> playerStats) {
        int sum = 0;
        for (Statistics statistics : playerStats) {
            sum += statistics.getAirKills();
        }
        return sum;
    }

    private int calcTotalScoreByPlayerId(List<Statistics> playerStats) {
        int sum = 0;
        for (Statistics statistics : playerStats) {
            sum += statistics.getScore();
        }
        return sum;
    }

    private int countAllWinsByPlayerId(List<Statistics> playerStats) {
        int counter = 0;
        for (Statistics statistics : playerStats) {
            if (statistics.isWon()) {
                counter += 1;
            }

        }
        return counter;
    }

    private int countAllLosesByPlayerId(List<Statistics> playerStats) {
        int counter = 0;
        for (Statistics statistics : playerStats) {
            if (!statistics.isWon()) {
                counter++;
            }

        }
        return counter;
    }
}
