package com.boilerr.coffeetablerest.repository;

import com.boilerr.coffeetablerest.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long>{
}
