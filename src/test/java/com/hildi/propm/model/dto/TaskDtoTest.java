package com.hildi.propm.model.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TaskDto Test")
class TaskDtoTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    @DisplayName("Test TaskDto constructor with all arguments")
    void testConstructorWithAllArguments() {
        // Given
        Long id = 1L;
        String name = "Test Task";
        String description = "This is a test task";
        Long projectId = 2L;
        Long roleId = 3L;
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime updatedDate = LocalDateTime.now();

        // When
        TaskDto taskDto = new TaskDto(id, name, description, projectId, roleId, createdDate, updatedDate);

        // Then
        assertAll("taskDto",
                () -> assertEquals(id, taskDto.getId()),
                () -> assertEquals(name, taskDto.getName()),
                () -> assertEquals(description, taskDto.getDescription()),
                () -> assertEquals(projectId, taskDto.getProjectId()),
                () -> assertEquals(roleId, taskDto.getRoleId()),
                () -> assertEquals(createdDate, taskDto.getCreatedDate()),
                () -> assertEquals(updatedDate, taskDto.getUpdatedDate())
        );
    }

    @Test
    @DisplayName("Test TaskDto constructor with required arguments")
    void testConstructorWithRequiredArguments() {
        // Given
        String name = "Test Task";

        // When
        TaskDto taskDto = TaskDto.builder()
                .name(name)
                .build();

        // Then
        assertAll("taskDto",
                () -> assertNull(taskDto.getId()),
                () -> assertEquals(name, taskDto.getName()),
                () -> assertNull(taskDto.getDescription()),
                () -> assertNull(taskDto.getProjectId()),
                () -> assertNull(taskDto.getRoleId()),
                () -> assertNull(taskDto.getCreatedDate()),
                () -> assertNull(taskDto.getUpdatedDate())
        );

        assertTrue(validator.validate(taskDto).isEmpty(), "Validation should pass for required fields");
    }

    @Test
    @DisplayName("Test TaskDto name field validation")
    void testNameValidation() {
        // Given
        TaskDto taskDto = TaskDto.builder()
                .name("")
                .build();

        // Then
        assertFalse(validator.validate(taskDto).isEmpty(), "Name should not be blank");

        taskDto.setName(" ");
        assertFalse(validator.validate(taskDto).isEmpty(), "Name should not be blank");
    }

    @Test
    @DisplayName("Test TaskDto no-args constructor")
    void haveNoArgsConstructor() {
        TaskDto taskDto = new TaskDto();
        assertNotNull(taskDto);
    }

    @Test
    @DisplayName("Test TaskDto constructor with all fields")
    void haveAllArgsConstructor() {
        Long id = 1L;
        String name = "Task 1";
        String description = "Description of Task 1";
        Long projectId = 1L;
        Long roleId = 2L;
        LocalDateTime createdDate = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedDate = LocalDateTime.now();
        TaskDto taskDto = new TaskDto(id, name, description, projectId, roleId, createdDate, updatedDate);

        assertEquals(id, taskDto.getId());
        assertEquals(name, taskDto.getName());
        assertEquals(description, taskDto.getDescription());
        assertEquals(projectId, taskDto.getProjectId());
        assertEquals(roleId, taskDto.getRoleId());
        assertEquals(createdDate, taskDto.getCreatedDate());
        assertEquals(updatedDate, taskDto.getUpdatedDate());
    }

    @Test
    @DisplayName("Test TaskDto builder")
    void haveBuilder() {
        Long id = 1L;
        String name = "Task 1";
        String description = "Description of Task 1";
        Long projectId = 1L;
        Long roleId = 2L;
        LocalDateTime createdDate = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedDate = LocalDateTime.now();
        TaskDto taskDto = TaskDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .projectId(projectId)
                .roleId(roleId)
                .createdDate(createdDate)
                .updatedDate(updatedDate)
                .build();

        assertEquals(id, taskDto.getId());
        assertEquals(name, taskDto.getName());
        assertEquals(description, taskDto.getDescription());
        assertEquals(projectId, taskDto.getProjectId());
        assertEquals(roleId, taskDto.getRoleId());
        assertEquals(createdDate, taskDto.getCreatedDate());
        assertEquals(updatedDate, taskDto.getUpdatedDate());
    }

    @Test
    @DisplayName("Test TaskDto setters and getters")
    void haveSettersAndGetters() {
        TaskDto taskDto = new TaskDto();
        Long id = 1L;
        String name = "Task 1";
        String description = "Description of Task 1";
        Long projectId = 1L;
        Long roleId = 2L;
        LocalDateTime createdDate = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedDate = LocalDateTime.now();

        taskDto.setId(id);
        taskDto.setName(name);
        taskDto.setDescription(description);
        taskDto.setProjectId(projectId);
        taskDto.setRoleId(roleId);
        taskDto.setCreatedDate(createdDate);
        taskDto.setUpdatedDate(updatedDate);

        assertEquals(id, taskDto.getId());
        assertEquals(name, taskDto.getName());
        assertEquals(description, taskDto.getDescription());
        assertEquals(projectId, taskDto.getProjectId());
        assertEquals(roleId, taskDto.getRoleId());
        assertEquals(createdDate, taskDto.getCreatedDate());
        assertEquals(updatedDate, taskDto.getUpdatedDate());
    }
}
