package com.biszczak.unilowski.marek.dcsworldtrackerapp.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatisticsDatesToSearchDto {

    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;

}
