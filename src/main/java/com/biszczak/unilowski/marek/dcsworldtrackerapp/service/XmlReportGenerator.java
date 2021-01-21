package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.PlayerStats;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.strategy.ReportGenerator;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
@Service
public class XmlReportGenerator implements ReportGenerator {

    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public File createReportForListOfMissions(List<StatisticsDto> statisticsDto, long playerId) throws IOException {
        String path = System.getProperty("java.io.tmpdir");
        String defaultBaseDir = path + "/report/";
        String finalPath = defaultBaseDir + playerId + ".xml";
        if (!Files.exists(Paths.get(defaultBaseDir))) {
            Files.createDirectory(Paths.get(defaultBaseDir));
        }
        xmlMapper.writeValue(new File(finalPath), statisticsDto);
        return new File(finalPath);
    }

    @Override
    public File createReportForTotalStats(PlayerStats playerStats, long playerId) throws IOException {
        String path = System.getProperty("java.io.tmpdir");
        String defaultBaseDir = path + "/report/";
        String finalPath = defaultBaseDir + "total_" + playerId + ".xml";
        if (!Files.exists(Paths.get(defaultBaseDir))) {
            Files.createDirectory(Paths.get(defaultBaseDir));
        }
        xmlMapper.writeValue(new File(finalPath), playerStats);
        return new File(finalPath);
    }
}
