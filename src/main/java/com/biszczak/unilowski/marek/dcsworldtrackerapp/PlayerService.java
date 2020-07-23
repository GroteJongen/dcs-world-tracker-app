package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService {

  PlayerRepository playerRepository;

  Optional<Player> getPlayerById(long id) {
    return playerRepository.findById(id);
  }

  public void savePlayerWithName(String name) {
    playerRepository.save(new Player(name));
  }

  List<Player> findAll() {
    return playerRepository.findAll();
  }

  List<Player> saveAll(Player[] players) {
    return playerRepository.saveAll(Arrays.asList(players));
  }
}
