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

    ProjectDto createProject(ProjectDto projectDto);

    ProjectDto updateProject(Long projectId, ProjectDto projectDto);

    void deleteProject(Long projectId);
}
