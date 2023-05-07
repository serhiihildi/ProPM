import com.hildi.propm.model.Project;
import com.hildi.propm.model.Task;
import com.hildi.propm.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskRepositoryTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private Task task;

    @BeforeEach
    void setUp() {
        Project project = Project.builder().id(1L).name("Project").description("Project description").build();
        task = Task.builder().id(1L).name("Task").description("Task description").project(project).build();
    }

    @Test
    @DisplayName("Test save task")
    void testSaveTask() {
        when(taskRepository.save(task)).thenReturn(task);
        Task savedTask = taskRepository.save(task);
        assertNotNull(savedTask);
        assertEquals(savedTask, task);
    }

    @Test
    @DisplayName("Test find all tasks")
    void testFindAllTasks() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskRepository.findAll()).thenReturn(taskList);
        List<Task> foundTasks = taskRepository.findAll();
        assertNotNull(foundTasks);
        assertEquals(foundTasks, taskList);
    }

    @Test
    @DisplayName("Test find task by id")
    void testFindTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        Optional<Task> foundTask = taskRepository.findById(1L);
        assertNotNull(foundTask);
        assertTrue(foundTask.isPresent());
        assertEquals(foundTask.get(), task);
    }

    @Test
    @DisplayName("Test find tasks by project")
    void testFindTasksByProject() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskRepository.findByProject(task.getProject())).thenReturn(taskList);
        List<Task> foundTasks = taskRepository.findByProject(task.getProject());
        assertNotNull(foundTasks);
        assertEquals(foundTasks, taskList);
    }

    @Test
    @DisplayName("Test find task by id and project id")
    void testFindTaskByIdAndProjectId() {
        when(taskRepository.findByIdAndProjectId(1L, task.getProject().getId())).thenReturn(Optional.of(task));
        Optional<Task> foundTask = taskRepository.findByIdAndProjectId(1L, task.getProject().getId());
        assertNotNull(foundTask);
        assertTrue(foundTask.isPresent());
        assertEquals(foundTask.get(), task);
    }

    @Test
    @DisplayName("Test delete task")
    void testDeleteTask() {
        taskRepository.deleteById(1L);
        assertFalse(taskRepository.findById(1L).isPresent());
    }

}
