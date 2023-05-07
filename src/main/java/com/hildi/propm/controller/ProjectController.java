package com.hildi.propm.controller;

import com.hildi.propm.model.dto.ProjectDto;
import com.hildi.propm.services.impl.ProjectServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/projects")
@Slf4j
public class ProjectController {

    private final ProjectServiceImpl projectService;

    @Autowired
    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        log.debug("Getting all projects");
        List<ProjectDto> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "project/projectsList";
    }

    @GetMapping("/{id}")
    public String getProjectById(@PathVariable("id") Long id, Model model) {
        log.debug("Getting project with id {}", id);
        ProjectDto project = projectService.getProjectById(id).get();
        model.addAttribute("project", project);
        return "project/project";
    }

    @GetMapping("/new")
    public String createProjectForm(ProjectDto project) {
        log.debug("Rendering create project form");
        return "project/newProject";
    }

    @PostMapping("/new")
    public String createProject(@Valid ProjectDto project, BindingResult result) {
        if (result.hasErrors()) {
            log.debug("Validation errors while creating project");
            return "project/newProject";
        }
        log.debug("Creating new project with name {}", project.getName());
        projectService.createProject(project);
        return "redirect:/projects/all";
    }

    @GetMapping("/{id}/edit")
    public String editProjectForm(@PathVariable("id") Long id, Model model) {
        log.debug("Rendering edit project form for project with id {}", id);
        ProjectDto project = projectService.getProjectById(id).get();
        model.addAttribute("project", project);
        return "project/editProject";
    }

    @PostMapping("/{id}/edit")
    public String editProject(@PathVariable("id") Long id, @Valid ProjectDto project, BindingResult result) {
        if (result.hasErrors()) {
            log.debug("Validation errors while editing project with id {}", id);
            return "project/editProject";
        }
        log.debug("Updating project with id {} and name {}", id, project.getName());
        project.setId(id);
        projectService.updateProject(id, project);
        return "redirect:/projects/all";
    }

    @GetMapping("/{id}/delete")
    public String deleteProject(@PathVariable("id") Long id) {
        log.debug("Deleting project with id {}", id);
        projectService.deleteProject(id);
        return "redirect:/projects/all";
    }
}
