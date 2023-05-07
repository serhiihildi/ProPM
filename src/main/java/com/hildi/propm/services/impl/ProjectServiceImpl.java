package com.hildi.propm.services.impl;

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

    // Возвращает список всех проектов
    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        log.info("Get all projects, count: {}", projects.size());
        return mapper.map(projects, ProjectDto.class);
    }

    // Возвращает проект по его id, если он существует
    @Override
    public Optional<ProjectDto> getProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            log.info("Get project by id: {}", id);
            return Optional.of(mapper.map(project, ProjectDto.class));
        } else {
            log.warn("Project with id {} not found", id);
            return Optional.empty();
        }
    }

    // Создает новый проект и возвращает его
    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = mapper.map(projectDto, Project.class);
        project = projectRepository.save(project);
        log.info("Create project with id: {}", project.getId());
        return mapper.map(project, ProjectDto.class);
    }

    // Обновляет проект с заданным id и возвращает обновленный проект
    @Override
    public Optional<ProjectDto> updateProject(Long id, ProjectDto projectDto) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project = projectRepository.save(project);
            log.info("Update project with id: {}", project.getId());
            return Optional.of(mapper.map(project, ProjectDto.class));
        } else {
            log.warn("Project with id {} not found", id);
            return Optional.empty();
        }
    }

    // Удаляет проект с заданным id и возвращает true, если удаление выполнено успешно
    @Override
    public boolean deleteProject(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            projectRepository.delete(projectOptional.get());
            log.info("Delete project with id: {}", id);
            return true;
        } else {
            log.warn("Project with id {} not found", id);
            return false;
        }
    }

}
