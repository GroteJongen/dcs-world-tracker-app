package com.biszczak.unilowski.marek.dcsworldtrackerapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "players")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {


    public Player(String name) {
        this.name = name;
    }

    private String login;

    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Mission> missions = new ArrayList<>();
}
