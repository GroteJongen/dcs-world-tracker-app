package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.UnrecognizedParameterGivenException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.PlayerStats;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.strategy.ReportGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Service
public class ReportGeneratorContext {

    @Autowired
    private final PlayerTotalStatsService playerTotalStatsService;
    @Autowired
    private final StatisticsDtoService statisticsDtoService;

    private final ReportGenerator reportGenerator;

    public Resource getPlayerStatsReportBasingOnTypeGivenByUser(String type, long playerId) throws IOException {
        if (type.equals("total")) {
            return getPlayerTotalStatsReport(playerTotalStatsService.getTotalStatsOfPlayerWithId(playerId), playerId);
        }
        if (type.equals("all")) {
            return getPlayerStatsReport(statisticsDtoService.getAllPlayerMissionStats(playerId), playerId);
        } else {
            throw new UnrecognizedParameterGivenException("Cannot recognize report generation parameter");
        }
    }


    private Resource getPlayerStatsReport(List<StatisticsDto> statisticsDto, long playerId) throws IOException {
        return new InputStreamResource(new FileInputStream(reportGenerator.createReportForListOfMissions(statisticsDto, playerId)));
    }

    private Resource getPlayerTotalStatsReport(PlayerStats playerStats, long playerId) throws IOException {
        return new InputStreamResource(new FileInputStream(reportGenerator.createReportForTotalStats(playerStats, playerId)));
    }

    @Scheduled(fixedRate = 5000)
    public void removeDir() throws IOException {
        FileUtils.deleteDirectory(new File(System.getProperty("java.io.tmpdir") + "/report"));
    }


}
