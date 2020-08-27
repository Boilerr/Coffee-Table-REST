package com.boilerr.coffeetablerest.repository;

import com.boilerr.coffeetablerest.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long questionId);

    @Query("SELECT s FROM Task s JOIN s.tags t WHERE t = (:tag)")
    List<Task> retrieveByTag(@Param("tag") String tag);

}
