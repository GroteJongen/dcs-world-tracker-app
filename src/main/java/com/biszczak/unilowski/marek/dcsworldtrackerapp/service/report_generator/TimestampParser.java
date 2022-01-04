package com.biszczak.unilowski.marek.dcsworldtrackerapp.service.report_generator;

import org.springframework.stereotype.Component;

@Component
public class TimestampParser {


  /*  public void parseDatesToTimestamps(MissionDatesDateDto missionDatesDateDto) throws ParseException {
        LocalDate localDateFrom = LocalDate.parse(missionDatesDateDto.getDateFrom());
        LocalDate localDateTo = LocalDate.parse(missionDatesDateDto.getDateTo());
        try {
            if (missionDatesDateDto.getDateTo() == null) {
                missionDatesDateDto.setDateTo(String.valueOf(Instant.now().toEpochMilli()));
            }
            missionDatesDateDto.setDateFrom(String.valueOf(localDateFrom));
            System.out.println(missionDatesDateDto.getDateFrom());
            missionDatesDateDto.setDateTo(String.valueOf(localDateTo));
            System.out.println(missionDatesDateDto.getDateTo());
        } catch (DateTimeException exc) {
            throw new WrongDateFormatPassedInRequestException("Wrong date format passed in request, check if date is correct and try again");
        }
    }*/
}
