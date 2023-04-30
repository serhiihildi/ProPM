package com.hildi.propm.repository;

import com.hildi.propm.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Project Repository Tests")
class ProjectRepositoryTest {
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    Project project;

    @BeforeEach
    void setUp() {
        // create a new project to find
        project = Project.builder()
                .id(1L)
                .name("Test Project")
                .description("This is a test project.")
                .build();
    }

    @Test
    @DisplayName("Save Project Test")
    void testSaveProject() {
        // configure the mock to return the saved project
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // save the project
        Project savedProject = projectRepository.save(project);

        // verify that the project was saved
        assertNotNull(savedProject, "Expected saved project not to be null");
        assertNotNull(savedProject.getId(), "Expected saved project id not to be null");
        assertEquals(project.getName(), savedProject.getName(), "Expected saved project name to match");
        assertEquals(project.getDescription(), savedProject.getDescription(), "Expected saved project description to match");

        // verify that the save method was called once
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    @DisplayName("Find Project By Id Test")
    void testFindProjectById() {
        // configure the mock to return the project
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        // find the project
        Optional<Project> foundProject = projectRepository.findById(1L);

        // verify that the project was found
        assertTrue(foundProject.isPresent(), "Expected project to be found");
        assertEquals(project, foundProject.get(), "Expected project to match");

        // verify that the findById method was called once
        verify(projectRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Find All Projects Test")
    void testFindAllProjects() {
        // find all projects
        projectRepository.findAll();

        // verify that the findAll method was called once
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Delete Project By Id Test")
    void testDeleteProjectById() {
        // configure the mock to return the saved project
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        // save the project
        Project savedProject = projectRepository.save(project);

        // delete the project
        projectRepository.deleteById(savedProject.getId());

        // verify that the project was deleted
        Optional<Project> deletedProject = projectRepository.findById(1L);
        assertFalse(deletedProject.isPresent(), "Expected project to be deleted but it still exists");

        // verify that the deleteById method was called once
        verify(projectRepository, times(1)).deleteById(1L);
    }
}
