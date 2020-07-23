package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
@AllArgsConstructor
public class MissionController {

  private MissionService missionService;

  @GetMapping
  public List<Mission> showAllMissions() {
    return missionService.findAll();
  }

  @PostMapping(value = "/add", consumes = "application/json")
  public List<Mission> addMission(@RequestBody Mission[] missions) {
    return this.missionService.saveAll(missions);
  }
}
