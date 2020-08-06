package com.biszczak.unilowski.marek.dcsworldtrackerapp.model;

import lombok.Builder;

@Builder
public class PlayerStats {

    private final int totalMissions;
    private final int totalKills;
    private final int totalGroundKills;
    private final int totalScore;
    private final int totalVictories;
    private final int totalLoses;

    public PlayerStats(int totalMissions, int totalKills, int totalGroundKills, int totalScore, int totalVictories, int totalLoses) {
        this.totalMissions = totalMissions;
        this.totalKills = totalKills;
        this.totalGroundKills = totalGroundKills;
        this.totalScore = totalScore;
        this.totalVictories = totalVictories;
        this.totalLoses = totalLoses;
    }

    public int getTotalMissions() {
        return totalMissions;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public int getTotalGroundKills() {
        return totalGroundKills;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getTotalVictories() {
        return totalVictories;
    }

    public int getTotalLoses() {
        return totalLoses;
    }
}
