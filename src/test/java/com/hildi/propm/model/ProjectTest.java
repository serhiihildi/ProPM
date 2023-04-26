package com.hildi.propm.model;

import com.hildi.propm.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {
    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void testCreateProject() {
        Project project = new Project("Project Test", "Project Test Description", LocalDate.now(), LocalDate.now().plusDays(7));
        Project savedProject = projectRepository.save(project);

        assertNotNull(savedProject);
        assertNotNull(savedProject.getId());
        assertEquals(project.getName(), savedProject.getName());
        assertEquals(project.getDescription(), savedProject.getDescription());
        assertEquals(project.getStartDate(), savedProject.getStartDate());
        assertEquals(project.getEndDate(), savedProject.getEndDate());
    }

    @Test
    void testUpdateProject() {
        Project project = new Project("Project Test", "Project Test Description", LocalDate.now(), LocalDate.now().plusDays(7));
        Project savedProject = projectRepository.save(project);

        assertNotNull(savedProject);
        assertNotNull(savedProject.getId());

        savedProject.setName("Project Test Update");
        Project updatedProject = projectRepository.save(savedProject);

        assertEquals(savedProject.getId(), updatedProject.getId());
        assertEquals(savedProject.getDescription(), updatedProject.getDescription());
        assertEquals(savedProject.getStartDate(), updatedProject.getStartDate());
        assertEquals(savedProject.getEndDate(), updatedProject.getEndDate());
        assertEquals(savedProject.getName(), updatedProject.getName());
    }

    @Test
    void testDeleteProject() {
        Project project = new Project("Project Test", "Project Test Description", LocalDate.now(), LocalDate.now().plusDays(7));
        Project savedProject = projectRepository.save(project);

        assertNotNull(savedProject);
        assertNotNull(savedProject.getId());

        Long id = savedProject.getId();
        projectRepository.delete(project);

        assertFalse(projectRepository.existsById(id));
    }

}