package com.hildi.propm.services;

import com.hildi.propm.dto.TaskDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface TaskService {
    List<TaskDto> getAllTasksByProjectId(Long projectId);

    TaskDto getTaskById(Long taskId);

    TaskDto createTask(Long projectId, TaskDto taskDto);

    TaskDto updateTask(Long projectId, Long taskId, TaskDto taskDto);

    boolean deleteTask(Long taskId, Long projectId);

    TaskDto getTaskByIdAndProjectId(Long taskId, Long projectId);

}

