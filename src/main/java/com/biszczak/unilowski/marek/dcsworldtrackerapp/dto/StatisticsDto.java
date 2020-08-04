package com.biszczak.unilowski.marek.dcsworldtrackerapp.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@AllArgsConstructor
public class StatisticsDto {
    private String playerName;
    private String missionName;
    private int airKills;
    private int groundKills;
    private int score;
    private boolean isWon;

}
