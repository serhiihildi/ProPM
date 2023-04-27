package com.hildi.propm.util;

import com.hildi.propm.dto.RoleDto;
import com.hildi.propm.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
    List<RoleDto> toDtoList(List<Role> roleList);
    List<Role> toEntityList(List<RoleDto> roleDtoList);
}

