package com.biszczak.unilowski.marek.dcsworldtrackerapp.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

import static org.hibernate.annotations.CascadeType.ALL;

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
    private String missionName;
    private String description;
    @OneToMany
    @Cascade(ALL)
    @JoinColumn(name = "missionId")
    private List<Statistics> statistics;
}
