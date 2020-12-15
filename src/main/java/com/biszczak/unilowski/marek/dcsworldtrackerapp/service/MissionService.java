package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.dto.MissionInfoDto;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Mission;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.MissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MissionService {
    @Autowired
    private final MissionRepository missionRepository;

    public Optional<Mission> getMissionById(Long id) {
        return missionRepository.findById(id);
    }

    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    public Mission findMissionByMissionName(String missionName){
        return missionRepository.findByMissionName(missionName);
    }

    public Mission saveMission(MissionInfoDto missionInfoDto){
        return missionRepository.save(new Mission(missionInfoDto.getMissionName(),missionInfoDto.getMissionDescription()));
    }

}
