package com.hildi.propm.services;

import com.hildi.propm.model.dto.TaskDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface TaskService {

    List<TaskDto> getAllTasks();

    Optional<TaskDto> getTaskById(Long taskId);


    Optional<TaskDto> createTask(TaskDto taskDto);

    Optional<TaskDto> updateTask(Long id, TaskDto taskDto);

    boolean deleteTask(Long id);
}

