package com.biszczak.unilowski.marek.dcsworldtrackerapp.controlers;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.PlayerDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.PlayerAlreadyExistException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Player;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController("/")
public class RegistrationController {

    @Autowired
    PlayerService playerService;

    @RequestMapping(value = "/foo",method = GET)
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
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
