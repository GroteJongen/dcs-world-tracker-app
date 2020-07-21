package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import lombok.*;

import javax.persistence.*;

@Table(name = "missions")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String missionName;
    @Column
    private String aircraftName;
    @Column
    private int groundKills;
    @Column
    private int airKills;
    @Column
    private int score;
    @Column
    private int landings;
    @Column
    private int deaths;
    @Column
    private String isVictory;
    @Column
    private String playerName;
}
