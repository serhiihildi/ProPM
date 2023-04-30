package com.hildi.propm.services.impl;

import com.hildi.propm.model.Role;
import com.hildi.propm.model.dto.RoleDto;
import com.hildi.propm.repository.RoleRepository;
import com.hildi.propm.services.RoleService;
import com.hildi.propm.util.mapper.CustomMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final CustomMapper mapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, CustomMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @SneakyThrows
    @Override
    public RoleDto getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + roleId));
        return mapper.map(role, RoleDto.class);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> mapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = mapper.map(roleDto, Role.class);
        Role savedRole = roleRepository.save(role);
        return mapper.map(savedRole, RoleDto.class);
    }

    @SneakyThrows
    @Override
    public RoleDto updateRole(Long id, RoleDto roleDto) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Role not found: " + id));
        existingRole.setName(roleDto.getName());
        existingRole.setDescription(roleDto.getDescription());
        Role updatedRole = roleRepository.save(existingRole);
        return mapper.map(updatedRole, RoleDto.class);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

}

