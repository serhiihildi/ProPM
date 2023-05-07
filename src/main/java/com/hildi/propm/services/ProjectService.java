package com.hildi.propm.services;

import com.hildi.propm.model.dto.ProjectDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface ProjectService {
    List<ProjectDto> getAllProjects();

    Optional<ProjectDto> getProjectById(Long projectId);

    ProjectDto createProject(ProjectDto projectDto);

    Optional<ProjectDto> updateProject(Long projectId, ProjectDto projectDto);

    boolean deleteProject(Long projectId);
}
