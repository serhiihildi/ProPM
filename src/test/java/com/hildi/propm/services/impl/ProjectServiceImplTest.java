package com.hildi.propm.services.impl;

import com.hildi.propm.dto.ProjectDto;
import com.hildi.propm.dto.RoleDto;
import com.hildi.propm.dto.UserDto;
import com.hildi.propm.model.Project;
import com.hildi.propm.repository.ProjectRepository;
import com.hildi.propm.util.ProjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTest {

    private static List<Project> projects;
    private static List<UserDto> users;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @BeforeAll
    public static void setUp() {
        // создаем тестовые проекты
        projects = new ArrayList<>();
        projects.add(new Project(1L, "Project 1", "Description 1", new ArrayList<>(), new ArrayList<>()));
        projects.add(new Project(2L, "Project 2", "Description 2", new ArrayList<>(), new ArrayList<>()));
        projects.add(new Project(3L, "Project 3", "Description 3", new ArrayList<>(), new ArrayList<>()));

        users = Arrays.asList(
                new UserDto(1L, "John", "Doe", "john.doe@example.com", "password",
                        new HashSet<>(Collections.singletonList(new RoleDto(1L, "ROLE_ADMIN")))),
                new UserDto(2L, "Jane", "Doe", "jane.doe@example.com", "password",
                        new HashSet<>(Collections.singletonList(new RoleDto(2L, "ROLE_USER")))));
    }

    @Test
    void getAllProjectsTest() {
        // задаем поведение мок-объектов
        when(projectRepository.findAll()).thenReturn(projects);
        when(projectMapper.toDto(projects.get(0))).thenReturn(new ProjectDto(projects.get(0).getId(), projects.get(0).getName(), projects.get(0).getDescription()));
        when(projectMapper.toDto(projects.get(1))).thenReturn(new ProjectDto(projects.get(1).getId(), projects.get(1).getName(), projects.get(1).getDescription()));
        when(projectMapper.toDto(projects.get(2))).thenReturn(new ProjectDto(projects.get(2).getId(), projects.get(2).getName(), projects.get(2).getDescription()));

        // вызываем тестируемый метод
        List<ProjectDto> result = projectService.getAllProjects();

        // проверяем результат
        assertEquals(3, result.size());
        assertEquals(projects.get(0).getName(), result.get(0).getName());
        assertEquals(projects.get(1).getName(), result.get(1).getName());
        assertEquals(projects.get(2).getName(), result.get(2).getName());
    }
}
