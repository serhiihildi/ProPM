package com.hildi.propm.repository;

import com.hildi.propm.model.Project;
import com.hildi.propm.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProject(Project project);

    Optional<Task> findByIdAndProjectId(Long taskId, Long projectId);
}

