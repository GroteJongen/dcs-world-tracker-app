package com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions;

public class WrongDateFormatPassedInRequestException extends RuntimeException {
    public WrongDateFormatPassedInRequestException(String message) {
        super(message);
    }
}
