package com.hildi.propm.services;

import com.hildi.propm.model.dto.ProjectDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface ProjectService {
    List<ProjectDto> getAllProjects();

    ProjectDto getProjectById(Long projectId);

    ProjectDto createProject(ProjectDto projectDto);

    ProjectDto updateProject(Long projectId, ProjectDto projectDto);

    void deleteProject(Long projectId);
}
