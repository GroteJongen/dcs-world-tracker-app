package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.PlayerDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Player;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService {

    @Transactional
    public Player registerNewPlayerAccount(PlayerDto playerDto)
            throws PlayerAlreadyExistException {

        if (loginExists(playerDto.getLogin())) {
            throw new PlayerAlreadyExistException(
                    "There is an account with that login: "
                            + playerDto.getLogin());
        }
        return null;
    }

    private boolean loginExists(String login) {
        return playerRepository.findByLogin(login).isPresent();
    }

    @Autowired
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
