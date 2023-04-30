package com.hildi.propm.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserDto class test")
class UserDtoTest {

    @InjectMocks
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto(1L, "John", "Doe", "johndoe@example.com", "password", null);
    }

    @Test
    @DisplayName("Test UserDto getters and setters")
    void testGettersAndSetters() {
        Long id = 1L;
        userDto.setId(id);
        assertEquals(id, userDto.getId());

        String name = "John Doe";
        userDto.setFirstName(name);
        assertEquals(name, userDto.getFirstName());

        String email = "johndoe@example.com";
        userDto.setEmail(email);
        assertEquals(email, userDto.getEmail());

        String password = "password";
        userDto.setPassword(password);
        assertEquals(password, userDto.getPassword());
    }

    @Test
    @DisplayName("Test creating UserDto object with no arguments")
    void testUserDtoNoArgsConstructor() {
        // Arrange

        // Act
        UserDto userDto = new UserDto();

        // Assert
        assertAll("UserDto",
                () -> assertNull(userDto.getId()),
                () -> assertNull(userDto.getFirstName()),
                () -> assertNull(userDto.getPassword()),
                () -> assertNull(userDto.getEmail())
        );
    }

    @Test
    @DisplayName("Test UserDto equals and hashCode")
    void testEqualsAndHashCode() {
        UserDto userDto1 = UserDto.builder().id(1L).firstName("John").lastName("Doe").email("john.doe@example.com")
                .password("password").roles(new HashSet<>()).build();
        UserDto userDto2 = UserDto.builder().id(1L).firstName("John").lastName("Doe").email("john.doe@example.com")
                .password("password").roles(new HashSet<>()).build();
        UserDto userDto3 = UserDto.builder().id(2L).firstName("Jane").lastName("Doe").email("jane.doe@example.com")
                .password("password").roles(new HashSet<>()).build();

        assertEquals(userDto1, userDto2);
        assertNotEquals(userDto1, userDto3);
        assertNotEquals(userDto2, userDto3);

        assertEquals(userDto1.hashCode(), userDto2.hashCode());
        assertNotEquals(userDto1.hashCode(), userDto3.hashCode());
        assertNotEquals(userDto2.hashCode(), userDto3.hashCode());
    }

    @Test
    @DisplayName("Test UserDto builder")
    void testBuilder() {
        Set<RoleDto> roles = new HashSet<>();
        roles.add(RoleDto.builder().id(1L).name("ROLE_ADMIN").build());
        roles.add(RoleDto.builder().id(2L).name("ROLE_USER").build());

        UserDto userDto = UserDto.builder().id(1L).firstName("John").lastName("Doe").email("john.doe@example.com")
                .password("password").roles(roles).build();

        assertNotNull(userDto);
        assertEquals(1L, userDto.getId());
        assertEquals("John", userDto.getFirstName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("john.doe@example.com", userDto.getEmail());
        assertEquals("password", userDto.getPassword());
        assertEquals(roles, userDto.getRoles());
    }
}
