package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {

  PlayerRepository playerRepository;

  @GetMapping("players/{id}")
  public Optional<Player> getplayerById(@RequestParam long id) {
    return playerRepository.findById(id);
  }

  @GetMapping("/all")
  public List<Player> getAllPlayers(){
    return playerRepository.findAll();
  }

  @PostMapping(value = "/add", consumes = "application/json")
  public List<Player> addPlayer(@RequestBody Player[] players) {
    return playerRepository.saveAll(Arrays.asList(players));
  }
}
