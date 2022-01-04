package com.biszczak.unilowski.marek.dcsworldtrackerapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerTotalStatsDto {

    private int totalMissions;
    private int totalAirKills;
    private int totalGroundKills;
    private int totalScore;
    private int totalVictories;
    private int totalLoses;
    private int totalDeaths;
    private double killDeathRatio;
    private double winLooseRatio;
}
