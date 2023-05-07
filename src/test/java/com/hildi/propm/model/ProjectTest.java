package com.hildi.propm.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@DisplayName("Testing Project class")
public class ProjectTest {

    private Project project;

    @BeforeEach
    public void setUp() {
        project = new Project();
    }

    @Nested
    @DisplayName("Testing user-related methods")
    class UserRelatedTests {

        @Test
        @DisplayName("Should add user to project")
        void shouldAddUserToProject() {
            // given
            User user = mock(User.class);

            // when
            project.addUser(user);

            // then
            assertTrue(project.getUsers().contains(user));
        }

        @Test
        @DisplayName("Should remove user from project")
        void shouldRemoveUserFromProject() {
            // given
            User user = mock(User.class);
            project.addUser(user);

            // when
            project.removeUser(user);

            // then
            assertFalse(project.getUsers().contains(user));
        }
    }

}
