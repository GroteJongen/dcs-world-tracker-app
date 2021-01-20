package com.biszczak.unilowski.marek.dcsworldtrackerapp.controlers;


import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.PlayerStats;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.PlayerTotalStatsService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/results")
@RequiredArgsConstructor
public class PlayerResultsController {

    @Autowired
    private final StatisticsDtoService statisticsDtoService;
    @Autowired
    private final PlayerTotalStatsService playerTotalStatsService;

    @RequestMapping( value = "/{id}", method = GET)
    public List<StatisticsDto> getResultsByPlayerID(@PathVariable long id) {
        return statisticsDtoService.addPlayerStatisticsToList(id);
    }

    @RequestMapping(value = "/total/{id}",method = GET)
    public PlayerStats getTotalResultsByPlayerId(@PathVariable long id){
        return playerTotalStatsService.getTotalStatsOfPlayerWithId(id);
    }
}
