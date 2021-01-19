package com.biszczak.unilowski.marek.dcsworldtrackerapp.repository;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    Optional<Statistics> findByMissionIdAndPlayerId(long missionId, long playerId);

    List<Statistics> findAllByPlayerId(long playerId);
}
