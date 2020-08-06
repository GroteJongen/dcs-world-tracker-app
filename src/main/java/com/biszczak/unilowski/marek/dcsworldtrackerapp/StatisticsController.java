package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsDtoService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final StatisticsDtoService statisticsDtoService;

    @GetMapping("/all")
    public List<Statistics> findAllStats() {
        return statisticsService.getAllStatistics();
    }

    @GetMapping("/missionInfo/{id}")
    public StatisticsDto findAllInfoForStatsWithId(@PathVariable long id) {
        return statisticsDtoService.createDtoForStatisticsWithId(id);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public void addStatistics(@RequestBody Statistics[] statistics) {
        statisticsService.saveStatistics(statistics);
    }
}
