package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Player;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public Optional<Player> getPlayerById(@RequestParam long id) {
        return playerService.getPlayerById(id);
    }

    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return playerService.findAll();
    }


    @PostMapping(value = "/add", consumes = "application/json")
    public List<Player> addPlayer(@RequestBody Player[] players) {
        return playerService.saveAll(players);
    }
}
