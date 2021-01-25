package com.biszczak.unilowski.marek.dcsworldtrackerapp.repository;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    Optional<Statistics> findByMissionIdAndPlayerId(long missionId, long playerId);

    List<Statistics> findAllByPlayerId(long playerId);

    List<Statistics> findAllByPlayerId(long playerId, Sort sort);

    @Query("SELECT * FROM statistics  WHERE date >= ?1 AND date <= ?2 AND playerId = ?3")
    List<Statistics> findAllByDate(long dateFrom, long dateTo, long playerId);
}
