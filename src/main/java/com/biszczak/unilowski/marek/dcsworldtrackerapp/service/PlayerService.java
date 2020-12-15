package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.PlayerDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.PlayerAlreadyExistException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Player;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService implements IUserService {

    @Transactional
    @Override
    public Player registerNewPlayerAccount(PlayerDto playerDto)
            throws PlayerAlreadyExistException {

        if (loginExists(playerDto.getLogin())) {
            throw new PlayerAlreadyExistException(
                    "There is an account with that login: "
                            + playerDto.getLogin());
        }
        Player player = new Player();
        player.setLogin(playerDto.getLogin());
        player.setPassword(playerDto.getPassword());
        return playerRepository.save(player);
    }

    private boolean loginExists(String login) {
        return playerRepository.findByLogin(login).isPresent();
    }

    @Autowired
    PlayerRepository playerRepository;

    public Optional<Player> getPlayerById(long id) {
        return playerRepository.findById(id);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }
}
