package com.hildi.propm.repository;

import com.hildi.propm.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("User Repository Tests")
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe", "johndoe@example.com", "password");
    }

    @Test
    @DisplayName("Save User Test")
    void testSaveUser() {
        // configure the mock to return the user
        when(userRepository.save(user)).thenReturn(user);

        // save the user
        User savedUser = userRepository.save(user);

        // verify that the user was saved
        assertEquals(user, savedUser);

        // verify that the save method was called once
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("Find User By Id Test")
    void testFindUserById() {
        // configure the mock to return the user
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // find the user
        Optional<User> foundUser = userRepository.findById(1L);

        // verify that the user was found
        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());

        // verify that the findById method was called once
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Find All Users Test")
    void testFindAllUsers() {
        // configure the mock to return a list of users
        List<User> userList = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(userList);

        // find all users
        List<User> foundUsers = userRepository.findAll();

        // verify that the users were found
        assertFalse(foundUsers.isEmpty());
        assertEquals(userList, foundUsers);

        // verify that the findAll method was called once
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Delete User Test")
    void testDeleteUser() {
        // configure the mock to return the saved project
        when(userRepository.save(any(User.class))).thenReturn(user);

        // save the project
        User savedProject = userRepository.save(user);

        // delete the user
        userRepository.deleteById(1L);

        // verify that the user was deleted
        Optional<User> deletedUser = userRepository.findById(1L);
        assertFalse(deletedUser.isPresent(), "Expected user to be deleted but it still exists");

        // verify that the deleteById method was called once
        verify(userRepository, times(1)).deleteById(1L);
    }
}
