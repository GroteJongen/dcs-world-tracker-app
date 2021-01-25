package com.biszczak.unilowski.marek.dcsworldtrackerapp.dto;



import lombok.*;


@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Setter
public class StatisticsDto {

    public StatisticsDto(Long missionId, Long playerId, int airKills, int groundKills, int score, boolean isWon) {
        this.missionId = missionId;
        this.playerId = playerId;
        this.airKills = airKills;
        this.groundKills = groundKills;
        this.score = score;
        this.isWon = isWon;
    }

    private Long missionId;
    private Long playerId;
    private String missionName;
    private int airKills;
    private int groundKills;
    private int score;
    private boolean isWon;

}
