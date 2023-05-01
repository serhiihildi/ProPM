package com.hildi.propm.controller;

import com.hildi.propm.model.dto.RoleDto;
import com.hildi.propm.services.RoleService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long roleId) {
        log.info("Getting role by ID: {}", roleId);
        RoleDto roleDto = roleService.getRoleById(roleId);
        log.info("Got role: {}", roleDto);
        return ResponseEntity.ok(roleDto);
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
        log.info("Creating new role: {}", roleDto);
        RoleDto createdRole = roleService.createRole(roleDto);
        log.info("Created role: {}", createdRole);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        log.info("Updating role with ID {}: {}", id, roleDto);
        RoleDto updatedRole = roleService.updateRole(id, roleDto);
        log.info("Updated role: {}", updatedRole);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        log.info("Deleting role with ID: {}", id);
        roleService.deleteRole(id);
        log.info("Role with ID {} deleted", id);
        return ResponseEntity.noContent().build();
    }
}

