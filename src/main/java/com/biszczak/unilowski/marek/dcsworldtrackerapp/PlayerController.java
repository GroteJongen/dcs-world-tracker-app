package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {

  PlayerService playerService;
  StatisticsService statisticsService;

  @GetMapping("players/{id}")
  public Optional<Player> getplayerById(@RequestParam long id) {
    return playerService.getPlayerById(id);
  }

  @GetMapping("/all")
  public List<Player> getAllPlayers() {
    return playerService.findAll();
  }

  @GetMapping("/results")
  public List<Statistics> getResultsByPlayerID(@RequestParam long id) {
    return statisticsService.getStatisticsByPlayerId(id);
  }

  @PostMapping(value = "/add", consumes = "application/json")
  public List<Player> addPlayer(@RequestBody Player[] players) {
    return playerService.saveAll(players);
  }
}
