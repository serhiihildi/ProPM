package com.hildi.propm.services;

import com.hildi.propm.model.dto.RoleDto;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface RoleService {
    RoleDto getRoleById(Long roleId);
    List<RoleDto> getAllRoles();
    RoleDto createRole(RoleDto roleDto);
    RoleDto updateRole(Long id, RoleDto roleDto) throws RoleNotFoundException;
    void deleteRole(Long roleId);
}

