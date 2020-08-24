package com.boilerr.coffeetablerest.repository;

import com.boilerr.coffeetablerest.model.DailyLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyLogRepository extends JpaRepository<DailyLog, Long>{
}
