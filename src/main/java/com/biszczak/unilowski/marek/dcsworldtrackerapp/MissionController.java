package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/missions")
public class MissionController {

  private MissionRepository missionRepository;

  @Autowired
  public MissionController(MissionRepository missionRepository) {
    this.missionRepository = missionRepository;
  }

  @GetMapping
  public List<Mission> showAllMissions() {
    return missionRepository.findAll();
  }

  @PostMapping(value = "/add", consumes = "application/json")
  public List<Mission> addMission(@RequestBody Mission[] missions) {
    return this.missionRepository.saveAll(Arrays.asList(missions));
  }


}
