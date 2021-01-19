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
        int totalGroundKills = calculateTotalGroundKillsByPlayerId(id);
        int totalAirKills = calcTotalAirKillsByPlayerId(id);
        int totalScore = calcTotalScoreByPlayerId(id);
        int totalWins = countAllWinsByPlayerId(id);
        int totalLoses = countAllLosesByPlayerId(id);
        int totalGames = statisticsService.getStatisticsByPlayerId(id).size();
        return new PlayerStats(totalGames, totalAirKills, totalGroundKills, totalScore, totalWins, totalLoses);
    }


    private int calculateTotalGroundKillsByPlayerId(long id) {
        List<Statistics> playerStats = statisticsService.getStatisticsByPlayerId(id);
        int sum = 0;
        for (Statistics statistics : playerStats) {
            sum += statistics.getGroundKills();
        }
        return sum;
    }

    private int calcTotalAirKillsByPlayerId(long id) {
        List<Statistics> playerStats = statisticsService.getStatisticsByPlayerId(id);
        int sum = 0;
        for (Statistics statistics : playerStats) {
            sum += statistics.getAirKills();
        }
        return sum;
    }

    private int calcTotalScoreByPlayerId(long id) {
        List<Statistics> playerStats = statisticsService.getStatisticsByPlayerId(id);
        int sum = 0;
        for (Statistics statistics : playerStats) {
            sum += statistics.getScore();
        }
        return sum;
    }

    private int countAllWinsByPlayerId(long id) {
        List<Statistics> playerStats = statisticsService.getStatisticsByPlayerId(id);
        int counter = 0;
        for (Statistics statistics : playerStats) {
            if (statistics.isWon()) {
                counter += 1;
            }

        }
        return counter;
    }

    private int countAllLosesByPlayerId(long id) {
        List<Statistics> playerStats = statisticsService.getStatisticsByPlayerId(id);
        int counter = 0;
        for (Statistics statistics : playerStats) {
            if (!statistics.isWon()) {
                counter++;
            }

        }
        return counter;
    }
}
