package com.hildi.propm.model;

import org.junit.Assert;
import org.junit.Test;

public class RoleTest {

    @Test
    public void testRoleCreation() {
        Role role = new Role();
        role.setName("Test role");

        Assert.assertEquals("Test role", role.getName());
    }
}
