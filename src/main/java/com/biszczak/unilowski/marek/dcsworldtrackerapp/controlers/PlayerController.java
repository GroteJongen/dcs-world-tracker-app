package com.biszczak.unilowski.marek.dcsworldtrackerapp.controlers;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.PlayerDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.PlayerAlreadyExistException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Player;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    @Autowired
    private final PlayerService playerService;

    @RequestMapping(method = GET)
    public Optional<Player> getPlayerById(@RequestParam long id) {
        return playerService.getPlayerById(id);
    }

    @RequestMapping(value = "/all", method = GET)
    public List<Player> getAllPlayers() {
        return playerService.findAll();
    }

    @RequestMapping(value = "/add",method = POST)
    public Player addPlayer(@RequestBody @Valid PlayerDto playerDto) throws PlayerAlreadyExistException {
        return playerService.registerNewPlayerAccount(playerDto);
    }
}
