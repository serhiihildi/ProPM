package com.hildi.propm.controller;

import com.hildi.propm.model.Role;
import com.hildi.propm.model.dto.RoleDto;
import com.hildi.propm.services.impl.RoleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/projects/{projectId}/roles")
public class RoleController {

    private final RoleServiceImpl roleService;

    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ModelAndView getAll() {
        log.debug("Getting all roles");
        ModelAndView modelAndView = new ModelAndView("rolesList");
        modelAndView.addObject("roles", roleService.getAll());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getById(@PathVariable("id") Long id) {
        log.debug("Getting role with id {}", id);
        ModelAndView modelAndView = new ModelAndView("role");
        modelAndView.addObject("role", roleService.getRoleById(id));
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView newRole() {
        log.debug("Creating new role");
        ModelAndView modelAndView = new ModelAndView("newRole");
        modelAndView.addObject("role", new Role());
        return modelAndView;
    }

    @PostMapping("/new")
    public ModelAndView createRole(@Valid @ModelAttribute("role") RoleDto role, BindingResult result) {
        if (result.hasErrors()) {
            log.debug("Validation errors occurred while creating role");
            ModelAndView modelAndView = new ModelAndView("newRole");
            modelAndView.addObject("role", role);
            return modelAndView;
        }
        log.debug("Creating role with name {}", role.getName());
        roleService.create(role);
        return new ModelAndView("redirect:/roles");
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editRole(@PathVariable("id") Long id) {
        log.debug("Editing role with id {}", id);
        ModelAndView modelAndView = new ModelAndView("editRole");
        modelAndView.addObject("role", roleService.getRoleById(id));
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateRole(@Valid @ModelAttribute("role") RoleDto role, BindingResult result) {
        if (result.hasErrors()) {
            log.debug("Validation errors occurred while editing role with id {}", role.getId());
            ModelAndView modelAndView = new ModelAndView("editRole");
            modelAndView.addObject("role", role);
            return modelAndView;
        }
        log.debug("Updating role with id {}", role.getId());
        roleService.updateRole(role.getId(), role);
        return new ModelAndView("redirect:/roles");
    }

    @PostMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") Long id) {
        log.debug("Deleting role with id {}", id);
        roleService.deleteRoleById(id);
        return new ModelAndView("redirect:/roles");
    }
}

