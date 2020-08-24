package com.boilerr.coffeetablerest.repository;

import com.boilerr.coffeetablerest.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
