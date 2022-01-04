package com.biszczak.unilowski.marek.dcsworldtrackerapp.controlers;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDatesToSearchDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.MissionInfoDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Mission;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.MissionService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.SearchService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsDtoService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/missions")
@AllArgsConstructor
public class MissionController {

    @Autowired
    private final MissionService missionService;
    @Autowired
    private final StatisticsDtoService statisticsDtoService;
    @Autowired
    private final StatisticsService statisticsService;
    @Autowired
    private final SearchService searchService;


    @RequestMapping(method = GET)
    public List<Mission> showAllMissions() {
        return missionService.findAll();
    }

    @RequestMapping(value = "/new", method = POST)
    public Mission addMission(@RequestBody MissionInfoDto missions) {
        return missionService.saveMission(missions);
    }

    @RequestMapping(value = "/stats/add", method = POST)
    public Statistics addStatsToMissionWithGivenName(@RequestBody StatisticsDto statisticsDto) throws IOException {
        return statisticsService.saveStatisticsForMission(statisticsDto);
    }

    @RequestMapping(value = "/byDate/{id}",method = POST)
    public List<Statistics> getByPlayerAndDte(@PathVariable long id, @RequestBody StatisticsDatesToSearchDto missionDatesDateDto) throws ParseException {
        return statisticsService.getALLPlayerStatisticsForPeriod(missionDatesDateDto,id);
    }


    @RequestMapping(value = "/missionInfo/{id}", method = GET)
    public StatisticsDto findAllInfoForStatsWithId(@PathVariable long id) {
        return statisticsDtoService.createDtoForStatisticsWithId(id);
    }

    @RequestMapping(value = "/mission/search", method = POST)
    public List<Mission> findMissionByMissionNameOrUserName(@RequestBody Map<String,String> params) {
        return searchService.searchForStatisticsWithGivenParams(params);
    }
}
