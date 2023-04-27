package com.hildi.propm.services.impl;

import com.hildi.propm.dto.RoleDto;
import com.hildi.propm.exception.ResourceNotFoundException;
import com.hildi.propm.model.Role;
import com.hildi.propm.repository.RoleRepository;
import com.hildi.propm.services.RoleService;
import com.hildi.propm.util.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleDto getRoleById(Long roleId) {
        return null;
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return null;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        return null;
    }

    @Override
    public RoleDto updateRole(Long id, RoleDto roleDto) {
        return null;
    }

    @Override
    public void deleteRole(Long roleId) {

    }
}
