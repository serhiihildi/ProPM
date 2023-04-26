package com.hildi.propm.model;

import org.junit.Assert;
import org.junit.Test;

public class TeamTest {

    @Test
    public void testTeamCreation() {
        Team team = new Team();
        team.setName("Test team");
        team.setDescription("This is a test team");

        Assert.assertEquals("Test team", team.getName());
        Assert.assertEquals("This is a test team", team.getDescription());
    }
}
