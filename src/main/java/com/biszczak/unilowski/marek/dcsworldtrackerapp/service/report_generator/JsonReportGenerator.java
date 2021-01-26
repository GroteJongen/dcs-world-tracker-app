package com.biszczak.unilowski.marek.dcsworldtrackerapp.service.report_generator;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.PlayerTotalStatsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.strategy.ReportGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class JsonReportGenerator implements ReportGenerator {


    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String path = System.getProperty("java.io.tmpdir");
    private final String defaultBaseDir = path + "/report/";

    @Override
    public File createReportForListOfMissions(List<StatisticsDto> statisticsDto, long playerId) throws IOException {
        String finalPath = defaultBaseDir + playerId + ".json";
        if (!Files.exists(Paths.get(defaultBaseDir))) {
            Files.createDirectory(Paths.get(defaultBaseDir));
        }
        objectMapper.writeValue(new File(finalPath), statisticsDto);
        return new File(finalPath);

    }

    @Override
    public File createReportForTotalStats(PlayerTotalStatsDto playerTotalStatsDto, long playerId) throws IOException {
        String finalPath = defaultBaseDir + "total_" + playerId + ".json";
        if (!Files.exists(Paths.get(defaultBaseDir))) {
            Files.createDirectory(Paths.get(defaultBaseDir));
        }
        objectMapper.writeValue(new File(finalPath), playerTotalStatsDto);
        return new File(finalPath);
    }

}
