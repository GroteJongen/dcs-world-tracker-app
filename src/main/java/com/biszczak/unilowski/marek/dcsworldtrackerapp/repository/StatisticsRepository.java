package com.biszczak.unilowski.marek.dcsworldtrackerapp.repository;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    List<Statistics> findAllByMissionId(long missionId);

    List<Statistics> findAllByPlayerId(long playerId);
}
