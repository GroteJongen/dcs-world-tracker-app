package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Player;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService {

  PlayerRepository playerRepository;

  public Optional<Player> getPlayerById(long id) {
    return playerRepository.findById(id);
  }

  public void savePlayerWithName(String name) {
    playerRepository.save(new Player(name));
  }

  public List<Player> findAll() {
    return playerRepository.findAll();
  }

  public List<Player> saveAll(Player[] players) {
    return playerRepository.saveAll(Arrays.asList(players));
  }
}
