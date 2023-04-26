package com.hildi.propm.model;

import org.junit.Assert;
import org.junit.Test;

public class ProjectTest {

    @Test
    public void testProjectCreation() {
        Project project = new Project();
        project.setName("Test project");
        project.setDescription("This is a test project");

        Assert.assertEquals("Test project", project.getName());
        Assert.assertEquals("This is a test project", project.getDescription());
    }
}
