package com.biszczak.unilowski.marek.dcsworldtrackerapp.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long missionId;

    private long playerId;

    private int score;

    private int airKills;

    private int groundKills;

    private boolean isWon;

    private long date;

}
