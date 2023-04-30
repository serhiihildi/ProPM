package com.hildi.propm.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Task Model Test")
class TaskTest {

    @Mock
    private Project project;

    @Mock
    private Role role;

    private final LocalDateTime createdDate = LocalDateTime.of(2022, 1, 1, 0, 0);
    private final LocalDateTime updatedDate = LocalDateTime.of(2022, 1, 2, 0, 0);

    @Test
    @DisplayName("Create Task with null values")
    void createTaskWithNullValues() {
        Task task = new Task(null, null, null, null, null, null, null);
        assertNull(task.getId());
        assertNull(task.getName());
        assertNull(task.getDescription());
        assertNull(task.getProject());
        assertNull(task.getRole());
        assertNull(task.getCreatedDate());
        assertNull(task.getUpdatedDate());
    }

    @Test
    @DisplayName("Create Task with non-null values")
    void createTaskWithNonNullValues() {
        Project project = new Project(1L, "Test Project", "Test project description", null, null);
        Role role = new Role(1L, "Test Role", "Test role description", null, project);
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime updatedDate = LocalDateTime.now().plusHours(1);

        Task task = new Task(1L, "Test Task", "Test task description", project, role, createdDate, updatedDate);

        assertEquals(1L, task.getId());
        assertEquals("Test Task", task.getName());
        assertEquals("Test task description", task.getDescription());
        assertEquals(project, task.getProject());
        assertEquals(role, task.getRole());
        assertEquals(createdDate, task.getCreatedDate());
        assertEquals(updatedDate, task.getUpdatedDate());
    }

    @Test
    @DisplayName("Equals and Hashcode methods")
    void equalsAndHashCodeMethods() {
        Task task1 = new Task(1L, "Test Task", "Test task description");
        Task task2 = new Task(1L, "Test Task", "Test task description");
        Task task3 = new Task(2L, "Another Task", "Another task description");

        // Test equals method
        assertEquals(task1, task2);
        assertNotEquals(task1, task3);

        // Test hashcode method
        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    @DisplayName("Test Getters and Setters")
    void testGettersAndSetters() {
        Task task = new Task();

        task.setId(1L);
        assertEquals(1L, task.getId());

        task.setName("Task 1");
        assertEquals("Task 1", task.getName());

        task.setDescription("Description 1");
        assertEquals("Description 1", task.getDescription());

        task.setProject(project);
        assertEquals(project, task.getProject());

        task.setRole(role);
        assertEquals(role, task.getRole());

        task.setCreatedDate(createdDate);
        assertEquals(createdDate, task.getCreatedDate());

        task.setUpdatedDate(updatedDate);
        assertEquals(updatedDate, task.getUpdatedDate());
    }
}
