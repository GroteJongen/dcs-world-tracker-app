package com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PlayerAlreadyExistException extends Throwable {
    public PlayerAlreadyExistException(@NotNull @NotEmpty String msg) {
    }
}
