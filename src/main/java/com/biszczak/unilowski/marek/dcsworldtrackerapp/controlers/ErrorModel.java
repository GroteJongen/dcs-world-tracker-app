package com.biszczak.unilowski.marek.dcsworldtrackerapp.controlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel {

    private String message;
    private int statusCode;
}
