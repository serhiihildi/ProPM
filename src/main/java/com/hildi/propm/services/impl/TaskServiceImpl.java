package com.hildi.propm.services.impl;

import com.hildi.propm.model.Task;
import com.hildi.propm.model.dto.TaskDto;
import com.hildi.propm.repository.TaskRepository;
import com.hildi.propm.services.TaskService;
import com.hildi.propm.util.mapper.CustomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final CustomMapper mapper;

    public TaskServiceImpl(TaskRepository taskRepository, CustomMapper mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        log.info("Get all tasks, count: {}", tasks.size());
        return mapper.map(tasks, TaskDto.class);
    }

    @Override
    public Optional<TaskDto> getTaskById(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            log.info("Get task by id: {}", id);
            return Optional.of(mapper.map(task, TaskDto.class));
        } else {
            log.warn("Task with id {} not found", id);
            return Optional.empty();
        }
    }

    @Override
    public Optional<TaskDto> createTask(TaskDto taskDto) {
        Task task = mapper.map(taskDto, Task.class);
        task = taskRepository.save(task);
        log.info("Create task with id: {}", task.getId());
        return Optional.of(mapper.map(task, TaskDto.class));
    }

    @Override
    public Optional<TaskDto> updateTask(Long id, TaskDto taskDto) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task = taskRepository.save(task);
            log.info("Update task with id: {}", task.getId());
            return Optional.of(mapper.map(task, TaskDto.class));
        } else {
            log.warn("Task with id {} not found", id);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteTask(Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            taskRepository.delete(taskOptional.get());
            log.info("Delete task with id: {}", id);
            return true;
        } else {
            log.warn("Task with id {} not found", id);
            return false;
        }
    }

}
