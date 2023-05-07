package com.hildi.propm.services;

import com.hildi.propm.model.dto.RoleDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface RoleService {
    List<RoleDto> getAll();

    Optional<RoleDto> getRoleById(Long id);

    Optional<RoleDto> create(@Valid RoleDto roleDto);


    Optional<RoleDto> updateRole(Long id, @Valid RoleDto roleDto);

    boolean deleteRoleById(Long id);
}
