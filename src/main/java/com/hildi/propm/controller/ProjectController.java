package com.hildi.propm.controller;

import com.hildi.propm.dto.ProjectDto;
import com.hildi.propm.dto.RoleDto;
import com.hildi.propm.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long projectId) {
        ProjectDto projectDto = projectService.getProjectById(projectId);
        if (projectDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto createdProject = projectService.createProject(projectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        ProjectDto updatedProject = projectService.updateProject(id, projectDto);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(projectId, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{projectId}/roles")
    public ResponseEntity<List<RoleDto>> getRolesByProjectId(@PathVariable Long projectId) {
        List<RoleDto> roleDtos = projectService.getRolesByProjectId(projectId);
        return ResponseEntity.ok(roleDtos);
    }

    @PostMapping("/{projectId}/roles")
    public ResponseEntity<RoleDto> createRoleForProject(@PathVariable Long projectId, @RequestBody RoleDto roleDto) {
        RoleDto createdRole = projectService.createProject(projectId, roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

    @PutMapping("/{projectId}/roles/{roleId}")
    public ResponseEntity<RoleDto> updateRoleForProject(@PathVariable Long projectId, @PathVariable Long roleId,
                                                        @RequestBody RoleDto roleDto) {
        RoleDto updatedRole = projectService.updateProject(projectId, roleId, roleDto);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{projectId}/roles/{roleId}")
    public ResponseEntity<Void> deleteRoleForProject(@PathVariable Long projectId, @PathVariable Long roleId) {
        projectService.deleteProject(projectId, roleId);
        return ResponseEntity.noContent().build();
    }
}

