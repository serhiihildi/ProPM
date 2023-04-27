package com.hildi.propm.services.impl;

import com.hildi.propm.dto.ProjectDto;
import com.hildi.propm.dto.RoleDto;
import com.hildi.propm.dto.TaskDto;
import com.hildi.propm.model.Project;
import com.hildi.propm.repository.ProjectRepository;
import com.hildi.propm.services.ProjectService;
import com.hildi.propm.util.ProjectMapper;
import com.hildi.propm.util.RoleMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final RoleMapper roleMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, RoleMapper roleMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id " + projectId));
        return projectMapper.toDto(project);
    }

    @Override
    public ProjectDto createProject(Long projectId, ProjectDto projectDto) {
        return null;
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        return null;
    }

    @Override
    public ProjectDto updateProject(Long projectId, ProjectDto projectDto) {
        return null;
    }

    @Override
    public void deleteProject(Long id, Long projectId) {

    }

    @Override
    public void deleteProject(Long projectId) {

    }

    @Override
    public List<RoleDto> getRolesByProjectId(Long projectId) {
        return null;
    }

    @Override
    public RoleDto createRole(Long projectId, RoleDto roleDto) {
        return null;
    }

    @Override
    public RoleDto updateRole(Long roleId, RoleDto roleDto) {
        return null;
    }

    @Override
    public void deleteRole(Long roleId) {

    }

    @Override
    public List<TaskDto> getTasksByProjectId(Long projectId) {
        return null;
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        return null;
    }

    @Override
    public TaskDto createTask(Long projectId, TaskDto taskDto) {
        return null;
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto taskDto) {
        return null;
    }

    @Override
    public void deleteTask(Long taskId) {

    }

    @Override
    public List<TaskDto> getTasksByAssignee(Long userId) {
        return null;
    }

    @Override
    public List<TaskDto> getTasksByAssigneeAndProjectId(Long userId, Long projectId) {
        return null;
    }

    @Override
    public List<TaskDto> getTasksByCreator(Long userId) {
        return null;
    }

    @Override
    public List<TaskDto> getTasksByCreatorAndProjectId(Long userId, Long projectId) {
        return null;
    }
}

