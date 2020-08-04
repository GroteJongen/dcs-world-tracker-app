package com.biszczak.unilowski.marek.dcsworldtrackerapp.repository;

import com.biszczak.unilowski.marek.dcsworldtrackerapp.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
