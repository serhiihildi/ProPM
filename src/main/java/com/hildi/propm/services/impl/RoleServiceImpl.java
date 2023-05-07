package com.hildi.propm.services.impl;

import com.hildi.propm.model.Role;
import com.hildi.propm.model.dto.RoleDto;
import com.hildi.propm.repository.RoleRepository;
import com.hildi.propm.services.RoleService;
import com.hildi.propm.util.mapper.CustomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Validated
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final CustomMapper mapper;

    public RoleServiceImpl(RoleRepository roleRepository, CustomMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public List<RoleDto> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> mapper.map(role, RoleDto.class))
                .toList();
    }

    @Override
    public Optional<RoleDto> getRoleById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            log.info("Get role by id: {}", id);
            return Optional.of(mapper.map(role, RoleDto.class));
        } else {
            log.warn("Role with id {} not found", id);
            return Optional.empty();
        }
    }

    @Override
    public Optional<RoleDto> create(@Valid RoleDto roleDto) {
        Role role = mapper.map(roleDto, Role.class);
        role = roleRepository.save(role);
        log.info("Role created with ID {}", role.getId());
        return Optional.ofNullable(mapper.map(role, RoleDto.class));
    }

    @Override
    public Optional<RoleDto> updateRole(Long id, @Valid RoleDto roleDto) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role = roleRepository.save(role);
            log.info("Role with ID {} edited", id);
            return Optional.ofNullable(mapper.map(role, RoleDto.class));
        } else {
            log.warn("Role with ID {} not found", id);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteRoleById(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            roleRepository.delete(optionalRole.get());
            log.info("Role with ID {} deleted", id);
            return true;
        } else {
            log.warn("Role with ID {} not found", id);
            return false;
        }
    }

}
