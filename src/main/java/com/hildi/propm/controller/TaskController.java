package com.hildi.propm.controller;

import com.hildi.propm.model.dto.TaskDto;
import com.hildi.propm.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/projects/{projectId}/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<List<TaskDto>> getAllTasksForProject(@PathVariable Long projectId) {
        log.info("Get all tasks for project with id {}", projectId);
        List<TaskDto> tasks = taskService.getAllTasksByProjectId(projectId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long projectId, @PathVariable Long taskId) {
        log.info("Get task with id {} for project with id {}", taskId, projectId);
        TaskDto task = taskService.getTaskByIdAndProjectId(taskId, projectId);
        if (task == null) {
            log.warn("Task with id {} for project with id {} not found", taskId, projectId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskDto> createTask(@PathVariable Long projectId, @RequestBody TaskDto taskDto) {
        log.info("Create task for project with id {}", projectId);
        TaskDto createdTask = taskService.createTask(projectId, taskDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();
        log.info("Task with id {} for project with id {} has been created", createdTask.getId(), projectId);
        return ResponseEntity.created(location).body(createdTask);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody TaskDto taskDto) {
        log.info("Update task with id {} for project with id {}", taskId, projectId);
        TaskDto updatedTask = taskService.updateTask(projectId, taskId, taskDto);
        if (updatedTask == null) {
            log.warn("Task with id {} for project with id {} not found", taskId, projectId);
            return ResponseEntity.notFound().build();
        }
        log.info("Task with id {} for project with id {} has been updated", taskId, projectId);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        log.info("Delete task with id {} for project with id {}", taskId, projectId);
        boolean deleted = taskService.deleteTask(taskId, projectId);
        if (deleted) {
            log.info("Task with id {} for project with id {} has been deleted", taskId, projectId);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Task with id {} for project with id {} not found", taskId, projectId);
            return ResponseEntity.notFound().build();
        }
    }

}
