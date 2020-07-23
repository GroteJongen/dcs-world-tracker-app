package com.biszczak.unilowski.marek.dcsworldtrackerapp;

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
  private int id;

  @Column private long missionID;

  @Column private long playerId;

  @Column private int score;

  @Column private int airKills;

  @Column private int groundKills;

  @Column private boolean isWon;
}
