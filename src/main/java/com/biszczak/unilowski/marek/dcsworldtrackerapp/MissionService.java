package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MissionService {

  private MissionRepository missionRepository;

  public Optional<Mission> getMissionById(long id) {
    return missionRepository.findById(id);
  }

  public void saveMission(String name, String description) {
    missionRepository.save(new Mission(name, description));
  }

  public List<Mission> findAll() {
    return missionRepository.findAll();
  }

  public List<Mission> saveAll(Mission[] missions) {
    return missionRepository.saveAll(Arrays.asList(missions));
  }
}
