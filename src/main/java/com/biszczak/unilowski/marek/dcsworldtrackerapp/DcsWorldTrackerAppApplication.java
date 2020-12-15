package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Mission;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Player;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.MissionRepository;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.PlayerRepository;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.StatisticsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;


@SpringBootApplication
public class DcsWorldTrackerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DcsWorldTrackerAppApplication.class, args);
        //TODO add exceptions and encapsulate all data save/read
        //TODO Start thinking of implementing spring security into this project

    }

    @Bean
    public CommandLineRunner dataInitializer(MissionRepository missionRepository, PlayerRepository playerRepository, StatisticsRepository statisticsRepository) {
        return (args) -> {
            playerRepository.save(new Player("Andrzej"));
            missionRepository.save(new Mission(1, "Andrzej leci jak szalooony", "Brak opisu", Collections.singletonList(new Statistics(1, 1, 1, 100, 2, 3, true))));
            missionRepository.save(new Mission(2, "Andrzej dolatuje do lotniska", "Brak opisu", Collections.singletonList((new Statistics(2, 2, 1, 200, 5, 1, false)))));
            missionRepository.save(new Mission(3, "Andrzej zladowal", "Brak opisu", Collections.singletonList(new Statistics(3, 3, 1, 300, 6, 5, true))));
        };
    }
}
