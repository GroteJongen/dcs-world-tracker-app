package com.biszczak.unilowski.marek.dcsworldtrackerapp.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class FilterCriteriaDto {

    private String paramName;
    private String sort;
}
