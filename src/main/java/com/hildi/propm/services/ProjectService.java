package com.hildi.propm.services;

import com.hildi.propm.dto.ProjectDto;
import com.hildi.propm.dto.RoleDto;
import com.hildi.propm.dto.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<ProjectDto> getAllProjects();
    ProjectDto getProjectById(Long projectId);
    ProjectDto createProject(Long projectId, ProjectDto projectDto);

    ProjectDto createProject(ProjectDto projectDto);

    ProjectDto updateProject(Long projectId, ProjectDto projectDto);
    void deleteProject(Long id, Long projectId);

    void deleteProject(Long projectId);

    List<RoleDto> getRolesByProjectId(Long projectId);
    RoleDto createRole(Long projectId, RoleDto roleDto);
    RoleDto updateRole(Long roleId, RoleDto roleDto);
    void deleteRole(Long roleId);
    List<TaskDto> getTasksByProjectId(Long projectId);
    TaskDto getTaskById(Long taskId);
    TaskDto createTask(Long projectId, TaskDto taskDto);
    TaskDto updateTask(Long taskId, TaskDto taskDto);
    void deleteTask(Long taskId);
    List<TaskDto> getTasksByAssignee(Long userId);
    List<TaskDto> getTasksByAssigneeAndProjectId(Long userId, Long projectId);
    List<TaskDto> getTasksByCreator(Long userId);
    List<TaskDto> getTasksByCreatorAndProjectId(Long userId, Long projectId);
}