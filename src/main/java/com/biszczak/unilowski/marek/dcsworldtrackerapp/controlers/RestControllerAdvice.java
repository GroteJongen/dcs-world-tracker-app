package com.biszczak.unilowski.marek.dcsworldtrackerapp.controlers;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.MissionDoesNotExistException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.PasswordDoesNotMatchException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.PlayerAlreadyExistException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.PlayerHasNoStatisticsOrDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(value = { PlayerHasNoStatisticsOrDoesNotExistsException.class, PlayerAlreadyExistException.class,
            MissionDoesNotExistException.class, PasswordDoesNotMatchException.class})
    protected ResponseEntity<ErrorModel> handleConflict(RuntimeException ex) {
        return  ResponseEntity.of(Optional.of(new ErrorModel(ex.getMessage(),HttpStatus.BAD_REQUEST.value())));
    }

}
