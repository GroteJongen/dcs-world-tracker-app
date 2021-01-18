package com.biszczak.unilowski.marek.dcsworldtrackerapp.controlers;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.MissionInfoDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.StatisticsDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Mission;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.MissionService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsDtoService;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @RequestMapping(method = GET)
    public List<Mission> showAllMissions() {
        return missionService.findAll();
    }

    @RequestMapping(value = "/create", method = POST)
    public Mission addMission(@RequestBody MissionInfoDto missions) {
        return missionService.saveMission(missions);
    }

    @RequestMapping(value = "/stats", method = POST)
    public Statistics addStatsToMissionWithGivenName(@RequestBody StatisticsDto statisticsDto) {
        return statisticsService.saveStatisticsForMissionWithName(statisticsDto);
    }

    @RequestMapping(value = "/missionInfo/{id}", method = GET)
    public StatisticsDto findAllInfoForStatsWithId(@PathVariable long id) {
        return statisticsDtoService.createDtoForStatisticsWithId(id);
    }
}
