package com.hildi.propm.services;

import com.hildi.propm.dto.ProjectDto;
import com.hildi.propm.model.Project;
import com.hildi.propm.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;
    private ProjectDto projectDto;

    @BeforeEach
    public void setUp() {
        project = new Project("Project 1", "Project description", LocalDate.now(), LocalDate.now().plusDays(7));
        projectDto = new ProjectDto(1L, "Project 1", "Project description", LocalDate.now(), LocalDate.now().plusDays(7));
    }

    @Test
    void testGetAllProjects() {
        List<Project> projects = Arrays.asList(project);
        when(projectRepository.findAll()).thenReturn(projects);

        List<ProjectDto> result = projectService.getAllProjects();

        assertEquals(1, result.size());
        assertEquals(projectDto, result.get(0));
    }

    @Test
    void testGetProjectById() throws EntityNotFoundException {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjectDto result = projectService.getProjectById(1L);

        assertEquals(projectDto, result);
    }

    @Test
    void testGetProjectByIdThrowsEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> {
            when(projectRepository.findById(2L)).thenReturn(Optional.empty());

            projectService.getProjectById(2L);
        });
    }

    @Test
    void testCreateProject() {
        when(projectRepository.save(project)).thenReturn(project);

        ProjectDto result = projectService.createProject(projectDto);

        assertEquals(projectDto, result);
    }

    @Test
    void testUpdateProject() throws EntityNotFoundException {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(projectRepository.save(project)).thenReturn(project);

        ProjectDto result = projectService.updateProject(1L, projectDto);

        assertEquals(projectDto, result);
    }

    @Test
    void testUpdateProjectThrowsEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> {
            when(projectRepository.findById(2L)).thenReturn(Optional.empty());

            projectService.updateProject(2L, projectDto);
        });
    }

    @Test
    void testDeleteProject() throws EntityNotFoundException {
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        projectService.deleteProject(1L);

        // verify that projectRepository's deleteById method was called once with 1L argument
    }

    @Test
    void testDeleteProjectThrowsEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> {
            when(projectRepository.findById(2L)).thenReturn(Optional.empty());

            projectService.deleteProject(2L);
        });
    }
}
