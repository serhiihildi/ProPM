package com.hildi.propm.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Project tests")
class ProjectTest {

    @Mock
    private Task task1;

    @InjectMocks
    private Project project;

    @Test
    @DisplayName("No-args constructor creates object")
    void testNoArgsConstructor() {
        Project project = new Project();
        assertNotNull(project);
    }

    @Test
    @DisplayName("ToString")
    void testToString() {
        Long id = 1L;
        String name = "Project 1";
        String description = "Description";
        Project project = new Project(id, name, description);

        String expected = "Project(id=1, name=Project 1, description=Description, roles=[])";
        assertEquals(expected, project.toString());
    }

    @Test
    @DisplayName("Test getters and setters")
    void testGettersAndSetters() {
        Long id = 1L;
        String name = "Project 1";
        String description = "Description 1";
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);

        project.setId(id);
        project.setName(name);
        project.setDescription(description);
        Set<Role> roleSet = Collections.singleton(Role.ROLE_USER);
        project.setRoles(roleSet);
        project.setTasks(tasks);

        assertEquals(id, project.getId());
        assertEquals(name, project.getName());
        assertEquals(description, project.getDescription());
        assertEquals(roleSet, project.getRoles());
        assertEquals(tasks, project.getTasks());
    }

    @Test
    @DisplayName("Test equals")
    void testEquals() {
        Long id1 = 1L;
        String name1 = "Project 1";
        String description1 = "Description 1";

        Long id2 = 2L;
        String name2 = "Project 2";
        String description2 = "Description 2";

        Project project1 = Project.builder()
                .id(id1)
                .name(name1)
                .description(description1)
                .build();
        Project project2 = Project.builder()
                .id(id1)
                .name(name1)
                .description(description1)
                .build();
        Project project3 = Project.builder()
                .id(id2)
                .name(name2)
                .description(description2)
                .build();

        assertEquals(project1, project2);
        assertNotEquals(project1, project3);
    }
}
