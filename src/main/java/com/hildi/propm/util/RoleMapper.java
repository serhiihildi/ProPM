package com.hildi.propm.util;

import com.hildi.propm.dto.RoleDto;
import com.hildi.propm.model.Role;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    Role toEntity(RoleDto roleDto);
    List<RoleDto> toDtoList(List<Role> roleList);
    List<Role> toEntityList(List<RoleDto> roleDtoList);
}

