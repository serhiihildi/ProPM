package com.hildi.propm.util.mapper;

import com.hildi.propm.model.Project;
import com.hildi.propm.model.Role;
import com.hildi.propm.model.Task;
import com.hildi.propm.model.dto.ProjectDto;
import com.hildi.propm.model.dto.RoleDto;
import com.hildi.propm.model.dto.TaskDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("CustomMapper tests")
class CustomMapperTest {

    private CustomMapper customMapper;

    @BeforeEach
    void setUp() {
        customMapper = new CustomMapper();
    }

    @DisplayName("Test mapping of Project to Dto")
    @Test
    void testMapProjectToDto() {
        // Given
        Project project = Project.builder()
                .id(1L)
                .name("Test project")
                .description("A sample project for testing purposes")
                .tasks(Arrays.asList(new Task(), new Task()))
                .roles(Arrays.asList(new Role(), new Role()))
                .build();

        // When
        ProjectDto projectDto = customMapper.map(project, ProjectDto.class);

        // Then
        assertNotNull(projectDto);
        assertEquals(project.getId(), projectDto.getId());
        assertEquals(project.getName(), projectDto.getName());
        assertEquals(project.getDescription(), projectDto.getDescription());
        assertEquals(project.getTasks().size(), projectDto.getTasks().size());
        assertEquals(project.getRoles().size(), projectDto.getRoles().size());
    }

    @DisplayName("Test mapping of Project Dto to entity")
    @Test
    void testMapDtoToProject() {
        // Given
        ProjectDto projectDto = ProjectDto.builder()
                .id(2L)
                .name("Test project Dto")
                .description("A sample project Dto for testing purposes")
                .tasks(Arrays.asList(new TaskDto(), new TaskDto()))
                .roles(Arrays.asList(new RoleDto(), new RoleDto()))
                .build();

        // When
        Project project = customMapper.map(projectDto, Project.class);

        // Then
        assertNotNull(project);
        assertEquals(projectDto.getId(), project.getId());
        assertEquals(projectDto.getName(), project.getName());
        assertEquals(projectDto.getDescription(), project.getDescription());
        assertEquals(projectDto.getTasks().size(), project.getTasks().size());
        assertEquals(projectDto.getRoles().size(), project.getRoles().size());
    }

}
