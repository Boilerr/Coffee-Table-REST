package com.boilerr.coffeetablerest.repository;

import com.boilerr.coffeetablerest.model.Answer;
import com.boilerr.coffeetablerest.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long questionId);
}