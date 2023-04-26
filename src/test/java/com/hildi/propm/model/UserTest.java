package com.hildi.propm.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void createUser() {
        User user = new User("JohnDoe", "john.doe@example.com");

        assertEquals("JohnDoe", user.getUsername());
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    void updateUser() {
        User user = new User("JohnDoe", "john.doe@example.com");
        user.setUsername("JaneDoe");
        user.setEmail("jane.doe@example.com");

        assertEquals("JaneDoe", user.getUsername());
        assertEquals("jane.doe@example.com", user.getEmail());
    }
}
