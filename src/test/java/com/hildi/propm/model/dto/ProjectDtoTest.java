package com.hildi.propm.model.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProjectDto class tests")
class ProjectDtoTest {

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        // Given
        ProjectDto projectDto1 = ProjectDto.builder().id(1L).name("Project1").description("Description1").build();
        ProjectDto projectDto2 = ProjectDto.builder().id(1L).name("Project1").description("Description1").build();
        ProjectDto projectDto3 = ProjectDto.builder().id(2L).name("Project2").description("Description2").build();

        // Then
        assertEquals(projectDto1, projectDto2);
        assertNotEquals(projectDto1, projectDto3);
    }

    @Test
    @DisplayName("Test no args constructor")
    void testNoArgsConstructor() {
        ProjectDto projectDto = new ProjectDto();
        assertNull(projectDto.getId());
        assertNull(projectDto.getName());
        assertNull(projectDto.getDescription());
        assertNull(projectDto.getUsers());
    }

    @Test
    @DisplayName("Test hash code")
    void testHashCode() {
        // Given
        ProjectDto projectDto1 = ProjectDto.builder().id(1L).name("Project1").description("Description1").build();
        ProjectDto projectDto2 = ProjectDto.builder().id(1L).name("Project1").description("Description1").build();
        ProjectDto projectDto3 = ProjectDto.builder().id(2L).name("Project2").description("Description2").build();

        // Then
        assertEquals(projectDto1.hashCode(), projectDto2.hashCode());
        assertNotEquals(projectDto1.hashCode(), projectDto3.hashCode());
    }
}
