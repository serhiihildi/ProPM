package com.hildi.propm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;

    @Mock
    private User user;

    @Mock
    private Project project;

    @Mock
    private Role role;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = Task.builder()
                .id(1L)
                .name("Test task")
                .description("Test description")
                .project(project)
                .role(role)
                .status(TaskStatus.NEW)
                .creator(user)
                .assignee(user)
                .build();
    }

    @DisplayName("Test getters and setters")
    @Test
    void testGettersAndSetters() {
        assertNotNull(task.getId());
        assertEquals("Test task", task.getName());
        assertEquals("Test description", task.getDescription());
        assertEquals(project, task.getProject());
        assertEquals(role, task.getRole());
        assertEquals(TaskStatus.NEW, task.getStatus());
        assertEquals(user, task.getCreator());
        assertEquals(user, task.getAssignee());
        LocalDateTime now = LocalDateTime.now();
        task.setCreatedDate(now);
        assertEquals(now, task.getCreatedDate());
        task.setUpdatedDate(now);
        assertEquals(now, task.getUpdatedDate());
    }

    @DisplayName("Test equals and hashCode")
    @Test
    void testEqualsAndHashCode() {
        Task task1 = Task.builder()
                .id(task.getId())
                .name("Test task")
                .description("Test description")
                .project(project)
                .role(role)
                .status(TaskStatus.NEW)
                .creator(user)
                .assignee(user)
                .build();
        Task task2 = Task.builder()
                .id(task.getId())
                .name("Test task 2")
                .description("Test description 2")
                .project(project)
                .role(role)
                .status(TaskStatus.IN_PROGRESS)
                .creator(user)
                .assignee(user)
                .build();
        assertEquals(task, task1);
        assertEquals(task.hashCode(), task1.hashCode());
    }

    @DisplayName("Test prePersist method")
    @Test
    void testPrePersist() {
        assertNull(task.getCreatedDate());
        assertNull(task.getUpdatedDate());
        task.prePersist();
        assertNotNull(task.getCreatedDate());
    }

    @DisplayName("Test toString method")
    @Test
    void testToString() {
        assertNotNull(task.toString());
    }
}
