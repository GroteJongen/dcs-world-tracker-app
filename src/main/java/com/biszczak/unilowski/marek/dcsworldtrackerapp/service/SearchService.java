package com.biszczak.unilowski.marek.dcsworldtrackerapp.service;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Mission;
import com.biszczak.unilowski.marek.dcsworldtrackerapp.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@RequiredArgsConstructor
public class SearchService {

    @Autowired
    private final MissionRepository missionRepository;

    private static final List<String> FIELD_NAMES = Arrays.asList("missionName", "missionDescription");

    public List<Mission> searchForStatisticsWithGivenParams(Map<String, String> params) {
        List<Specification<Mission>> specifications = new ArrayList<>();
        params.entrySet().stream()
                .filter(x -> FIELD_NAMES.contains(x.getKey()) && x.getValue() != null && !x.getValue().isEmpty())
                .forEach(x -> specifications.add(searchSpecification(x.getKey(), x.getValue())));

        Specification<Mission> result = null;
        if (specifications.size() > 0) {
            result = specifications.get(0);
            for (int i = 1; i < specifications.size(); i++) {
                result = Objects.requireNonNull(Specification.where(result)).and(specifications.get(i));
            }
        }
        return missionRepository.findAll(result);
    }

    static Specification<Mission> searchSpecification(String paramName, String name) {
        return (stats, cq, cb) -> cb.equal(stats.get(paramName), name);
    }

}
