package com.hildi.propm.repository;

import com.hildi.propm.model.Project;
import com.hildi.propm.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName("Task Repository Test")
class TaskRepositoryTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private Project project;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        project = new Project(1L, "Project A", "Description A");

        task1 = new Task("Task A", "Description A", project);
        task1.setId(1L);

        task2 = new Task("Task B", "Description B", project);
        task2.setId(2L);
    }

    @Test
    @DisplayName("Save Task Test")
    void testSaveTask() {
        when(taskRepository.save(task1)).thenReturn(task1);

        Task savedTask = taskRepository.save(task1);

        assertEquals(task1, savedTask, "Saved task should be equal to the original task");

        verify(taskRepository, times(1)).save(task1);
    }

    @Test
    @DisplayName("Find Task By Id Test")
    void testFindTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        Optional<Task> foundTask = taskRepository.findById(1L);

        assertTrue(foundTask.isPresent(), "Expected task to be found, but it was not");

        assertEquals(task1, foundTask.get(), "Found task should be equal to the original task");

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Find All Tasks Test")
    void testFindAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        List<Task> foundTasks = taskRepository.findAll();

        assertEquals(tasks, foundTasks, "Found tasks should be equal to the original tasks");

        verify(taskRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Delete Task Test")
    void testDeleteTask() {
        doNothing().when(taskRepository).deleteById(1L);

        taskRepository.deleteById(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Find By Project Test")
    void testFindByProject() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);

        when(taskRepository.findByProject(project)).thenReturn(tasks);

        List<Task> foundTasks = taskRepository.findByProject(project);
        assertEquals(tasks.size(), foundTasks.size(), "Number of found tasks should be equal to the number of expected tasks");
        assertEquals(tasks.get(0).getId(), foundTasks.get(0).getId(), "Found task should have the same id as the expected task");
        assertEquals(tasks.get(0).getName(), foundTasks.get(0).getName(), "Found task should have the same name as the expected task");
        assertEquals(tasks.get(0).getDescription(), foundTasks.get(0).getDescription(), "Found task should have the same description as the expected task");
        assertEquals(tasks.get(0).getProject().getId(), foundTasks.get(0).getProject().getId(), "Found task should have the same project id as the expected task");
    }

    @Test
    @DisplayName("Find By Id And Project Id Test")
    void testFindByIdAndProjectId() {
        when(taskRepository.findByIdAndProjectId(task1.getId(), project.getId())).thenReturn(Optional.of(task1));

        Optional<Task> foundTask = taskRepository.findByIdAndProjectId(task1.getId(), project.getId());
        assertEquals(task1.getId(), foundTask.get().getId(), "Found task should have the same id as the expected task");
        assertEquals(task1.getName(), foundTask.get().getName(), "Found task should have the same name as the expected task");
        assertEquals(task1.getDescription(), foundTask.get().getDescription(), "Found task should have the same description as the expected task");
        assertEquals(task1.getProject().getId(), foundTask.get().getProject().getId(), "Found task should have the same project id as the expected task");
    }

}
