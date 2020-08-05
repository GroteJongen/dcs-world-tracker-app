package com.biszczak.unilowski.marek.dcsworldtrackerapp.model;

import lombok.*;

import javax.persistence.*;

@Table(name = "missions")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mission {


    public Mission(String missionName, String description) {
        this.missionName = missionName;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String missionName;

    @Column
    private String description;
}
