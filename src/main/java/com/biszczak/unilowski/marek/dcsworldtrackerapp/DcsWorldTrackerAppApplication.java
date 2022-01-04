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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.Collections;


@SpringBootApplication
@EnableSwagger2
public class DcsWorldTrackerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DcsWorldTrackerAppApplication.class, args);

    }

    @Bean
    public CommandLineRunner dataInitializer(MissionRepository missionRepository, PlayerRepository playerRepository) {
        return (args) -> {
            playerRepository.save(new Player("Player1"));
            playerRepository.save(new Player("Player2"));
            missionRepository.save(new Mission(1, "Pustynna Burza", "Brak opisu", Collections.singletonList(new Statistics(1, 1, 1, 100, 2, 0, 3, true, LocalDateTime.of(2021, 1, 11, 10, 11, 12)))));
            missionRepository.save(new Mission(2, "Tropikalna Burza", "Brak opisu", Collections.singletonList((new Statistics(2, 2, 1, 200, 5, 2, 1, false, LocalDateTime.of(2021, 2, 12, 12, 13, 14))))));
            missionRepository.save(new Mission(3, "Podzwrotnikowa Burza", "Brak opisu", Collections.singletonList(new Statistics(3, 3, 1, 300, 6, 1, 5, true, LocalDateTime.of(2021, 1, 15, 13, 14, 16)))));
            missionRepository.save(new Mission(4, "Burzowa Burza", "Brak opisu", Collections.singletonList(new Statistics(4, 3, 1, 600, 6, 2, 5, true, LocalDateTime.of(2021, 1, 16, 13, 4, 16)))));

        };
    }
}
