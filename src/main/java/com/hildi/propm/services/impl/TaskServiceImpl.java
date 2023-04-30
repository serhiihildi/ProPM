package com.hildi.propm.services.impl;

import com.hildi.propm.model.Project;
import com.hildi.propm.model.Task;
import com.hildi.propm.model.dto.TaskDto;
import com.hildi.propm.repository.ProjectRepository;
import com.hildi.propm.repository.TaskRepository;
import com.hildi.propm.services.TaskService;
import com.hildi.propm.util.mapper.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final CustomMapper mapper;

    @Autowired
    public TaskServiceImpl(ProjectRepository projectRepository, TaskRepository taskRepository, CustomMapper mapper) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TaskDto> getAllTasksByProjectId(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id " + projectId));
        List<Task> tasks = taskRepository.findByProject(project);
        return tasks.stream()
                .map(task -> mapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            return mapper.map(task, TaskDto.class);
        } else {
            throw new EntityNotFoundException("Task not found with id " + taskId);
        }
    }

    @Override
    public TaskDto createTask(Long projectId, TaskDto taskDto) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            Task task = mapper.map(taskDto, Task.class);
            task.setProject(project);
            Task savedTask = taskRepository.save(task);
            return mapper.map(savedTask, TaskDto.class);
        } else {
            throw new EntityNotFoundException("Project not found with id " + projectId);
        }
    }

    @Override
    public TaskDto updateTask(Long projectId, Long taskId, TaskDto taskDto) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Optional<Task> optionalTask = taskRepository.findByIdAndProjectId(taskId, projectId);
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();
                task.setName(taskDto.getName());
                task.setDescription(taskDto.getDescription());
//                task.setStatus(taskDto.getStatus());
//                task.setDueDate(taskDto.getDueDate());
//                task.setAssignee(userMapper.toEntity(taskDto.getAssignee()));
                Task updatedTask = taskRepository.save(task);
                return mapper.map(updatedTask, TaskDto.class);
            } else {
                throw new EntityNotFoundException("Task not found with id " + taskId + " and projectId " + projectId);
            }
        } else {
            throw new EntityNotFoundException("Project not found with id " + projectId);
        }
    }


    @Override
    public boolean deleteTask(Long taskId, Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return false;
        }
        Project project = optionalProject.get();

        Optional<Task> optionalTask = project.getTasks().stream().filter(t -> t.getId().equals(taskId)).findFirst();
        if (optionalTask.isEmpty()) {
            return false;
        }
        Task task = optionalTask.get();

        project.getTasks().remove(task);
        projectRepository.save(project);

        return true;
    }


    public TaskDto getTaskByIdAndProjectId(Long taskId, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project with id " + projectId + " not found"));
        Task task = project.getTasks().stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + taskId + " not found in project with id " + projectId));
        return mapper.map(task, TaskDto.class);
    }

}
