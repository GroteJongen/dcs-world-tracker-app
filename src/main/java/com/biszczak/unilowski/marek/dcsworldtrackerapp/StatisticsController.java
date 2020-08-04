package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDtoService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stats")
@AllArgsConstructor
public class StatisticsController {

    StatisticsService statisticsService;
    StatisticsDtoService statisticsDtoService;

    @GetMapping("/all")
    public List<Statistics> findAllStats(){
        return statisticsService.getAllStatistics();
    }

    @GetMapping("/missionInfo")
    public StatisticsDto findAllInfoForStatsWithId(@RequestParam long id){
        return statisticsDtoService.createDtoForStatisticsWithId(id);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public void addStatistics(@RequestBody Statistics[] statistics){
         statisticsService.saveStatistics(statistics);
    }
}
