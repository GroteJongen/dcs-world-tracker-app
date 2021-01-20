package com.biszczak.unilowski.marek.dcsworldtrackerapp.dto;



import lombok.*;


@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Setter
public class StatisticsDto {

    private Long missionId;
    private Long playerId;
    private String missionName;
    private int airKills;
    private int groundKills;
    private int score;
    private boolean isWon;

}
