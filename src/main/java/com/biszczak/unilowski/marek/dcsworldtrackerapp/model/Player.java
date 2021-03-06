package com.biszczak.unilowski.marek.dcsworldtrackerapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "players")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {


    public Player(String name) {
        this.name = name;
    }
    @Column
    private String login;

    @Column
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Mission> missions = new ArrayList<>();
}
