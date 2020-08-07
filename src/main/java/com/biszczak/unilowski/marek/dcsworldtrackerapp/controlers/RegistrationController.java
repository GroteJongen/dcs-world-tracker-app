package com.biszczak.unilowski.marek.dcsworldtrackerapp.controlers;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.PlayerDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Player;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.PlayerAlreadyExistException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller("/user")
public class RegistrationController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        PlayerDto playerDto = new PlayerDto();
        model.addAttribute("user", playerDto);
        return "registration";
    }

    @PostMapping("/register")
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") @Valid PlayerDto playerDto,
             HttpServletRequest request, Errors errors) {

        try {
            Player registered = playerService.registerNewPlayerAccount(playerDto);
        } catch (PlayerAlreadyExistException uaeEx) {
            return null;
        }
        return new ModelAndView("registration", "user", playerDto);
    }

}
