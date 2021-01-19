package com.biszczak.unilowski.marek.dcsworldtrackerapp.controlers;


import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.PlayerStats;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.PlayerTotalStatsService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class PlayerResultsController {

    @Autowired
    private final StatisticsDtoService statisticsDtoService;
    @Autowired
    private final PlayerTotalStatsService playerTotalStatsService;

    @GetMapping("/{id}")
    public List<StatisticsDto> getResultsByPlayerID(@PathVariable long id) {
        return statisticsDtoService.addPlayerStatisticsToList(id);
    }

    @GetMapping("/total/{id}")
    public PlayerStats getTotalResultsByPlayerId(@PathVariable long id){
        return playerTotalStatsService.getTotalStatsOfPlayerWithId(id);
    }
}
