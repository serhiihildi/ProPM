package com.hildi.propm.services.impl;

import com.hildi.propm.exception.ResourceNotFoundException;
import com.hildi.propm.model.Project;
import com.hildi.propm.model.dto.ProjectDto;
import com.hildi.propm.repository.ProjectRepository;
import com.hildi.propm.services.ProjectService;
import com.hildi.propm.util.mapper.CustomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final CustomMapper mapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, CustomMapper mapper) {
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return mapper.map(projects, ProjectDto.class);
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            return mapper.map(project, ProjectDto.class);
        } else {
            throw new ResourceNotFoundException("Project with id " + id + " not found");
        }
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = mapper.map(projectDto, Project.class);
        project = projectRepository.save(project);
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        log.debug("Updating project with id {}: {}", id, projectDto);
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));
        projectRepository.save(project);
        ProjectDto updatedProjectDto = mapper.map(project, ProjectDto.class);
        log.debug("Updated project: {}", updatedProjectDto);
        return updatedProjectDto;
    }


    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));

        projectRepository.delete(project);
    }
}

