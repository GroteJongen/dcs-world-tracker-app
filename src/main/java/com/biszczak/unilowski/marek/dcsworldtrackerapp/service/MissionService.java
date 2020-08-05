package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Mission;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.MissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public Optional<Mission> getMissionById(long id) {
        return missionRepository.findById(id);
    }

    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    public List<Mission> saveAll(Mission[] missions) {
        return missionRepository.saveAll(Arrays.asList(missions));
    }
}
