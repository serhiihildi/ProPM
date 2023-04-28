package com.hildi.propm.util;

import com.hildi.propm.dto.RoleDto;
import com.hildi.propm.model.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
    List<RoleDto> toDtoList(List<Role> roleList);
    Set<Role> toEntityList(List<RoleDto> roleDtoList);
    Set<Role> toEntityList(Set<RoleDto> roles);
}

