package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Mission;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public List<Mission> showAllMissions() {
        return missionService.findAll();
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public List<Mission> addMission(@RequestBody Mission[] missions) {
        return this.missionService.saveAll(missions);
    }
}
