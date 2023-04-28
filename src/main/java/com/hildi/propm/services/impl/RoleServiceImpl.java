package com.hildi.propm.services.impl;

import com.hildi.propm.model.dto.RoleDto;
import com.hildi.propm.repository.RoleRepository;
import com.hildi.propm.services.RoleService;
import com.hildi.propm.util.mapper.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
