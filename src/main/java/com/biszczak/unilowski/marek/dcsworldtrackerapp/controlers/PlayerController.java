package com.biszczak.unilowski.marek.dcsworldtrackerapp.controlers;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.FilterCriteriaDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.PlayerDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.exceptions.PlayerAlreadyExistException;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Player;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.PlayerStats;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {

    @Autowired
    private final PlayerService playerService;
    @Autowired
    private final PlayerTotalStatsService playerTotalStatsService;
    @Autowired
    private final StatisticsDtoService statisticsDtoService;
    @Autowired
    private final ReportGeneratorContext reportGeneratorContext;


    @RequestMapping(method = GET)
    public Optional<Player> getPlayerById(@RequestParam long id) {
        return playerService.getPlayerById(id);
    }

    @RequestMapping(value = "/all", method = GET)
    public List<Player> getAllPlayers() {
        return playerService.findAll();
    }

    @RequestMapping(value = "/add", method = POST)
    public Player addPlayer(@RequestBody @Valid PlayerDto playerDto) throws PlayerAlreadyExistException {
        return playerService.registerNewPlayerAccount(playerDto);
    }

    @RequestMapping(value = "/total/{id}", method = GET)
    public PlayerStats getTotalResultsByPlayerId(@PathVariable long id) {
        return playerTotalStatsService.getTotalStatsOfPlayerWithId(id);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public List<StatisticsDto> getResultsByPlayerID(@PathVariable long id, @RequestBody FilterCriteriaDto filterCriteriaDto) {
        return statisticsDtoService.getAllSortedResults(id, filterCriteriaDto);
    }

    @RequestMapping(value = "/report/{id}/{reportType}", method = GET)
    public Resource generatePlayerRaport(@PathVariable long id, HttpServletResponse response, @PathVariable String reportType) throws IOException {
        String headerName = "attachment;filename=" + id;
        response.addHeader("Content-disposition", headerName);
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        return reportGeneratorContext.getPlayerStatsReportBasingOnTypeGivenByUser(reportType, id);
    }


}
