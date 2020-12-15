package com.biszczak.unilowski.marek.dcsworldtrackerapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
