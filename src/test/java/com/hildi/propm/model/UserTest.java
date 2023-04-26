package com.hildi.propm.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTest {

    @Test
    public void testCreateUser() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void testSetAndGetId() {
        User user = new User();
        Long id = 1L;
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void testSetAndGetFirstName() {
        User user = new User();
        String username = "john";
        user.setFirstName(username);
        assertEquals(username, user.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        User user = new User();
        String username = "john";
        user.setLastName(username);
        assertEquals(username, user.getLastName());
    }

    @Test
    public void testSetAndGetEmail() {
        User user = new User();
        String email = "john.doe@example.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testSetAndGetPassword() {
        User user = new User();
        String password = "password123";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }
}
