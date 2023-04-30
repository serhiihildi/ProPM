package com.hildi.propm.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("User class test")
@ExtendWith(MockitoExtension.class)
class UserTest {

    @Mock
    private Role role;

    @InjectMocks
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "John", "Doe", "johndoe@example.com", "password");
    }

    @Test
    @DisplayName("Test No-args Constructor")
    void testNoArgsConstructor() {
        User user = new User();

        assertNull(user.getId());
        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
    }

    @Test
    @DisplayName("User model should be instantiated correctly")
    void shouldInstantiateUser() {
        Assertions.assertAll(
                () -> assertEquals("John", user.getFirstName()),
                () -> assertEquals("Doe", user.getLastName()),
                () -> assertEquals("johndoe@example.com", user.getEmail()),
                () -> assertEquals("password", user.getPassword())
        );
    }

    @Test
    @DisplayName("User should have roles")
    void shouldHaveRoles() {
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);
        assertEquals(1, user.getRoles().size());
    }

    @Test
    @DisplayName("User should have ID")
    void shouldHaveId() {
        user.setId(1L);
        assertEquals(1L, user.getId());
    }

    @Test
    @DisplayName("User should be equal to another User with the same ID")
    void shouldBeEqual() {
        User anotherUser = new User(1L, "John", "Doe", "johndoe@example.com", "password");
        anotherUser.setId(user.getId());
        assertEquals(user, anotherUser);
    }

    @Test
    @DisplayName("User should not be equal to another User with a different ID")
    void shouldNotBeEqual() {
        User anotherUser = new User("John", "Doe", "johndoe@example.com", "password");
        anotherUser.setId(2L);
        Assertions.assertNotEquals(user, anotherUser);
    }

    @Test
    @DisplayName("User should have correct toString representation")
    void shouldHaveCorrectToString() {
        String expectedToString = "User(id=1, firstName=John, lastName=Doe, email=johndoe@example.com, password=password, roles=[])";
        assertEquals(expectedToString, user.toString());
    }

    @Test
    @DisplayName("User should have correct hash code")
    void shouldHaveCorrectHashCode() {
        User anotherUser = new User("John", "Doe", "johndoe@example.com", "password");
        assertEquals(user.hashCode(), anotherUser.hashCode());
    }
}
