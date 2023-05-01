package com.hildi.propm.controller;

import com.hildi.propm.model.dto.ProjectDto;
import com.hildi.propm.services.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getProjects() {
        log.info("getProjects method is called");
        List<ProjectDto> projectDtoList = projectService.getAllProjects();
        log.debug("Retrieved projects: {}", projectDtoList);
        return ResponseEntity.ok(projectDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        log.info("getProjectById method is called with id {}", id);
        ProjectDto projectDto = projectService.getProjectById(id);
        log.debug("Retrieved project: {}", projectDto);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        log.info("createProject method is called with projectDto {}", projectDto);
        ProjectDto createdProjectDto = projectService.createProject(projectDto);
        URI uri = URI.create("/projects/" + createdProjectDto.getId());
        log.debug("Created project: {}", createdProjectDto);
        return ResponseEntity.created(uri).body(createdProjectDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        log.info("updateProject method is called with id {} and projectDto {}", id, projectDto);
        ProjectDto updatedProjectDto = projectService.updateProject(id, projectDto);
        log.debug("Updated project: {}", updatedProjectDto);
        return ResponseEntity.ok(updatedProjectDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        log.info("deleteProject method is called with id {}", id);
        projectService.deleteProject(id);
        log.debug("Deleted project with id {}", id);
        return ResponseEntity.noContent().build();
    }

}

