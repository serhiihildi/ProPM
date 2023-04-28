package com.hildi.propm.services;

import com.hildi.propm.dto.RoleDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface RoleService {
    RoleDto getRoleById(Long roleId);
    List<RoleDto> getAllRoles();
    RoleDto createRole(RoleDto roleDto);
    RoleDto updateRole(Long id, RoleDto roleDto);
    void deleteRole(Long roleId);
}

