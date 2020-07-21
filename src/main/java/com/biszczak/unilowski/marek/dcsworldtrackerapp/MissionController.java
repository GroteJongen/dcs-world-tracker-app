package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/missions")
public class MissionController {

  private MissionRepository missionRepository;

  @Autowired
  public MissionController(MissionRepository missionRepository) {
    this.missionRepository = missionRepository;
  }

  @GetMapping()
  public String showAllMissions() {
    return missionRepository.findAll().toString();
  }

  /*@PostMapping(value = "/getMissions", consumes = "application/json")
  public List<Mission> addMission(@RequestBody List<Mission> missions) {
    return missions;
  }*/
}
