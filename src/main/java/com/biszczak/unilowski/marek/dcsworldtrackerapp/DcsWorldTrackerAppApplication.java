package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Mission;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Player;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.MissionRepository;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Collections;


@SpringBootApplication
public class DcsWorldTrackerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DcsWorldTrackerAppApplication.class, args);

    }

    @Bean
    public CommandLineRunner dataInitializer(MissionRepository missionRepository, PlayerRepository playerRepository) {
        return (args) -> {
            playerRepository.save(new Player("Andrzej"));
            playerRepository.save(new Player("Zdziszek"));
            missionRepository.save(new Mission(1, "Andrzej leci jak szalooony", "Brak opisu", Collections.singletonList(new Statistics(1, 1, 1, 100, 2, 3, true, LocalDateTime.of(2021,1,11,10,11,12)))));
            missionRepository.save(new Mission(2, "Andrzej dolatuje do lotniska", "Brak opisu", Collections.singletonList((new Statistics(2, 2, 1, 200, 5, 1, false,LocalDateTime.of(2021,2,12,12,13,14))))));
            missionRepository.save(new Mission(3, "Andrzej zladowal", "Brak opisu", Collections.singletonList(new Statistics(3, 3, 1, 300, 6, 5, true,LocalDateTime.of(2021,1,15,13,14,16)))));
            missionRepository.save(new Mission(4, "Andrzej andrzej jedzie do hangaru", "Brak opisu", Collections.singletonList(new Statistics(4, 3, 1, 600, 6, 5, true,LocalDateTime.of(2021,1,16,13,4,16)))));

        };
    }
}
