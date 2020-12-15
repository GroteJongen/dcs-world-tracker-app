package com.biszczak.unilowski.marek.dcsworldtrackerapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStats {

    private int totalMissions;
    private int totalKills;
    private int totalGroundKills;
    private int totalScore;
    private int totalVictories;
    private int totalLoses;
}
