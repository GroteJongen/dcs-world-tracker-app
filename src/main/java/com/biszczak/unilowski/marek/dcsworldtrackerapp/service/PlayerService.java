package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.PlayerDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.PasswordDoesNotMatchException;
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

    @Autowired
    private final PlayerRepository playerRepository;

    @Transactional
    @Override
    public Player registerNewPlayerAccount(PlayerDto playerDto) throws PlayerAlreadyExistException {
        if(!playerDto.getPassword().equals(playerDto.getMatchingPassword())) {
            throw new PasswordDoesNotMatchException("Password does not match");
        }
        if (loginExists(playerDto.getLogin())) {
            throw new PlayerAlreadyExistException("There is an account with that login: " + playerDto.getLogin());
        }
        Player player = createPlayerFromDto(playerDto);
        return playerRepository.save(player);
    }

    private Player createPlayerFromDto(PlayerDto playerDto){
        return Player.builder()
                .login(playerDto.getLogin())
                .name(playerDto.getName())
                .password(playerDto.getPassword())
                .build();
    }

    private boolean loginExists(String login) {
        return playerRepository.findByLogin(login).isPresent();
    }

    public Optional<Player> getPlayerById(long id) {
        return playerRepository.findById(id);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }


}
