package com.biszczak.unilowski.marek.dcsworldtrackerapp;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends CrudRepository<Mission, Long> {
}
