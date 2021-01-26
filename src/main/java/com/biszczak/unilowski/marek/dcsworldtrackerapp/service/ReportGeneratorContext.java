package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDatesToSearchDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.NoSuchFileFormatException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.UnrecognizedParameterGivenException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.PlayerStats;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.report_generator.JsonReportGenerator;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.report_generator.TimestampParser;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.report_generator.XmlReportGenerator;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.strategy.ReportGenerator;
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
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Service
public class ReportGeneratorContext {


    @Autowired
    private final StatisticsDtoService statisticsDtoService;
    @Autowired
    private final TimestampParser timestampParser;
    @Autowired
    private final StatisticsService statisticsService;
    private Map<String, ReportGenerator> generatorMap = new HashMap<>();
    private ReportGenerator reportGenerator;

    public ReportGeneratorContext(StatisticsService statisticsService, TimestampParser timestampParser, StatisticsDtoService statisticsDtoService) {
        this.statisticsDtoService = statisticsDtoService;
        this.timestampParser = timestampParser;
        this.statisticsService = statisticsService;
        generatorMap.put("xml", new XmlReportGenerator());
        generatorMap.put("json", new JsonReportGenerator());
    }


    public Resource getPlayerStatsReportBasingOnTypeGivenByUser(String type, long playerId, String format) throws IOException {
        setProperStrategy(format);
        if (type.equals("total")) {
            return getPlayerTotalStatsReport(statisticsService.calculateTotalStatisticsForPlayer(playerId), playerId);
        }
        if (type.equals("all")) {
            return getPlayerStatsReport(statisticsDtoService.getAllPlayerMissionStats(playerId), playerId);
        } else {
            throw new UnrecognizedParameterGivenException("Cannot recognize report generation parameter");
        }
    }

    public Resource getPlayerStatsReportBasingOnTypeGivenByUser(long playerId, String format, StatisticsDatesToSearchDto missionDatesDateDto) throws IOException, ParseException {
        setProperStrategy(format);
        return getPlayerTotalStatsReport(statisticsService.calculateTotalStatisticsForPlayerBasingOnDateQuery(playerId, missionDatesDateDto), playerId);
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

    private void setProperStrategy(String format) {
        if (generatorMap.get(format) == null) {
            throw new NoSuchFileFormatException("No such file format to generate report");
        }
        setReportGenerator(generatorMap.get(format));

    }


}
