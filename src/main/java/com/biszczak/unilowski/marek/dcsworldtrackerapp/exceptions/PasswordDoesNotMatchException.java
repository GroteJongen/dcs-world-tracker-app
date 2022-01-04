package com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions;

public class PasswordDoesNotMatchException extends RuntimeException {
    public PasswordDoesNotMatchException(String message) {
        super(message);
    }
}
