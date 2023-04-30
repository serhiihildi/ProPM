package com.hildi.propm.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

@DisplayName("Role class test")
class RoleTest {

    private Role role;

    @BeforeEach
    public void setUp() {
        role = new Role();
    }

    @Test
    @DisplayName("Test setting and getting id")
    void testSetAndGetId() {
        Long id = 1L;
        role.setId(id);
        Assertions.assertEquals(id, role.getId());
    }

    @Test
    @DisplayName("Test setting and getting name")
    void testSetAndGetName() {
        String name = "testName";
        role.setName(name);
        Assertions.assertEquals(name, role.getName());
    }

    @Test
    @DisplayName("Test setting and getting description")
    void testSetAndGetDescription() {
        String description = "testDescription";
        role.setDescription(description);
        Assertions.assertEquals(description, role.getDescription());
    }

    @Test
    @DisplayName("Test setting and getting users")
    void testSetAndGetUsers() {
        User user = Mockito.mock(User.class);
        Set<User> users = new HashSet<>();
        users.add(user);
        role.setUsers(users);
        Assertions.assertEquals(users, role.getUsers());
    }

    @Test
    @DisplayName("Test setting and getting project")
    void testSetAndGetProject() {
        Project project = Mockito.mock(Project.class);
        role.setProject(project);
        Assertions.assertEquals(project, role.getProject());
    }

    @Test
    @DisplayName("Test equals method")
    void testEquals() {
        Long id = 1L;
        Role role1 = new Role(id, "testName", "testDescription");
        Role role2 = new Role(id, "testName", "testDescription");
        Assertions.assertEquals(role1, role2);
    }

    @Test
    @DisplayName("Test hashCode method")
    void testHashCode() {
        Long id = 1L;
        Role role1 = new Role(id, "testName", "testDescription");
        Role role2 = new Role(id, "testName", "testDescription");
        Assertions.assertEquals(role1.hashCode(), role2.hashCode());
    }
}
