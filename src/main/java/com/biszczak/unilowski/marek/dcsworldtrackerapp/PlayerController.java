package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Player;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.PlayerService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsDtoService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayerController {

  PlayerService playerService;
  StatisticsDtoService statisticsDtoService;

  @GetMapping
  public Optional<Player> getPlayerById(@RequestParam long id) {
    return playerService.getPlayerById(id);
  }

  @GetMapping("/all")
  public List<Player> getAllPlayers() {
    return playerService.findAll();
  }

  @GetMapping("/results/{id}")
  public List<StatisticsDto> getResultsByPlayerID(@PathVariable long id) {
    return statisticsDtoService.createDtoFroAllStatisticsWithPlayerId(id);
  }

  @PostMapping(value = "/add", consumes = "application/json")
  public List<Player> addPlayer(@RequestBody Player[] players) {
    return playerService.saveAll(players);
  }
}
