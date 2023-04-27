package com.hildi.propm.services.impl;

import com.hildi.propm.dto.ProjectDto;
import com.hildi.propm.exception.ResourceNotFoundException;
import com.hildi.propm.model.Project;
import com.hildi.propm.repository.ProjectRepository;
import com.hildi.propm.services.ProjectService;
import com.hildi.propm.util.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projectMapper.toDtoList(projects);
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            return projectMapper.toDto(project);
        } else {
            throw new ResourceNotFoundException("Project with id " + id + " not found");
        }
    }

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    @Override
    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));

        projectMapper.updateProjectFromDto(projectDto, project);

        Project updatedProject = projectRepository.save(project);
        return projectMapper.toDto(updatedProject);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));

        projectRepository.delete(project);
    }
}

