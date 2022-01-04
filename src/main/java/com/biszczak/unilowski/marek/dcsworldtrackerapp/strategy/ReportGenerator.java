package com.biszczak.unilowski.marek.dcsworldtrackerapp.strategy;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.PlayerTotalStatsDto;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ReportGenerator {

    File createReportForListOfMissions(List<StatisticsDto> statisticsDto, long playerId) throws IOException;
    File createReportForTotalStats(PlayerTotalStatsDto playerTotalStatsDto, long playerId) throws IOException;
}
