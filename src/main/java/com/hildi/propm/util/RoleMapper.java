package com.hildi.propm.util;

import com.hildi.propm.dto.RoleDto;
import com.hildi.propm.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role role);

    Role toEntity(RoleDto roleDto);
}
