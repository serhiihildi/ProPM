package com.hildi.propm.controller;

import com.hildi.propm.model.Project;
import com.hildi.propm.model.dto.ProjectDto;
import com.hildi.propm.services.impl.ProjectServiceImpl;
import com.hildi.propm.util.mapper.CustomMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/projects")
@Api(value = "/api/projects")
public class ProjectController {

    private final ProjectServiceImpl projectService;
    private final CustomMapper mapper;

    public ProjectController(ProjectServiceImpl projectService, CustomMapper mapper) {
        this.projectService = projectService;
        this.mapper = mapper;
    }

    @GetMapping
    @ApiOperation(value = "Выводит список всех проектов")
    public String getProjects(Model model) {
        log.info("getProjects method is called");
        List<ProjectDto> projectDtoList = projectService.getAllProjects();
        log.debug("Retrieved projects: {}", projectDtoList);
        model.addAttribute("projects", projectDtoList);
        return "projectsList";
    }

    @GetMapping("/{id}/edit")
    @ApiOperation(value = "Выводит страницу для редактирования проекта")
    public String getEditProjectPage(@PathVariable Long id, Model model) {
        log.info("getEditProjectPage method is called with id {}", id);
        ProjectDto projectDto = projectService.getProjectById(id);
        log.debug("Retrieved project: {}", projectDto);
        model.addAttribute("project", projectDto);
        return "editProject";
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Отображает страницу проекта")
    public ModelAndView getProjectPage(@PathVariable Long id) {
        log.info("getProjectPage method is called with id {}", id);
        ProjectDto projectDto = projectService.getProjectById(id);
        log.debug("Retrieved project: {}", projectDto);
        ModelAndView mav = new ModelAndView("project");
        mav.addObject("project", projectDto);
        return mav;
    }

    @GetMapping("/{id}/p/")
    public String viewProject(@PathVariable Long id, Model model) {
        Optional<Project> project = Optional.of(mapper.map(projectService.getProjectById(id), Project.class));
        model.addAttribute("project", project.get());
        return "project";
    }

    @GetMapping("/new")
    public String showNewProjectForm(Model model) {
        log.info("Rendering new project form");
        model.addAttribute("project", new Project());
        return "newProject";
    }

    @PostMapping
    @ApiOperation(value = "Создает новый проект")
    public String createProject(@ModelAttribute("project") ProjectDto projectDto) {
        log.info("createProject method is called with projectDto {}", projectDto);
        ProjectDto createdProjectDto = projectService.createProject(projectDto);
        log.debug("Created project: {}", createdProjectDto);
        return "redirect:/projects/" + createdProjectDto.getId();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновляет данные проекта по его id")
    public String updateProject(@PathVariable Long id, @ModelAttribute("project") ProjectDto projectDto) {
        log.info("updateProject method is called with id {} and projectDto {}", id, projectDto);
        projectService.updateProject(id, projectDto);
        log.debug("Updated project with id {}", id);
        return "redirect:/projects/" + id;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удаляет проект по его id")
    public String deleteProject(@PathVariable Long id) {
        log.info("deleteProject method is called with id {}", id);
        projectService.deleteProject(id);
        log.debug("Deleted project with id {}", id);
        return "redirect:/projects";
    }

}

