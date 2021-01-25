package com.biszczak.unilowski.marek.dcsworldtrackerapp.service.report_generator;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.MissionDatesDateDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.WrongDateFormatPassedInRequestException;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.Instant;
@Component
public class TimestampParser {


    public MissionDatesDateDto parseDatesToTimestamps(MissionDatesDateDto missionDatesDateDto){
        try {
            missionDatesDateDto.setDateFrom(String.valueOf(Instant.parse(missionDatesDateDto.getDateFrom()).toEpochMilli()));
            missionDatesDateDto.setDateTo(String.valueOf(Instant.parse(missionDatesDateDto.getDateTo()).toEpochMilli()));
            return missionDatesDateDto;
        }catch (DateTimeException exc){
            throw new WrongDateFormatPassedInRequestException("Wrong date format passed in request, check if date is correct and try again");
        }
    }
}
