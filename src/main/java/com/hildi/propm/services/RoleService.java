package com.hildi.propm.services;

import com.hildi.propm.dto.RoleDto;
import com.hildi.propm.exception.ResourceNotFoundException;
import com.hildi.propm.model.Role;
import com.hildi.propm.repository.RoleRepository;
import com.hildi.propm.util.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public RoleDto createRole(RoleDto roleDto) {
        Role role = roleMapper.toEntity(roleDto);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDto(savedRole);
    }

    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    public RoleDto getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
        return roleMapper.toDto(role);
    }

    public RoleDto updateRole(Long id, RoleDto roleDto) {
        Role role = roleMapper.toEntity(roleDto);
        role.setId(id);
        roleRepository.save(role);
        return roleDto;
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}


