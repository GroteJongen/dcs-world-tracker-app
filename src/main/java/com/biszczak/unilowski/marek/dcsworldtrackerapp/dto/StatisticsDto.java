package com.biszczak.unilowski.marek.dcsworldtrackerapp.dto;



import lombok.*;


@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Setter
public class StatisticsDto {

    private Long playerId;
    private long missionId;
    private int airKills;
    private int groundKills;
    private int score;
    private boolean isWon;

}
