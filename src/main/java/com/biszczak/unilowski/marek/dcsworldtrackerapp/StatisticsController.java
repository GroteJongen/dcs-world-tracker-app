package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stats")
@AllArgsConstructor
public class StatisticsController {

    StatisticsService statisticsService;

    @GetMapping("/all")
    public List<Statistics> findAllStats(){
        return statisticsService.getAllStatistics();
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public void addStatistics(@RequestBody Statistics[] statistics){
         statisticsService.saveStatistics(statistics);
    }
}
