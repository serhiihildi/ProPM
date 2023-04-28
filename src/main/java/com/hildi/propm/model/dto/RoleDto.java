package com.hildi.propm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class RoleDto {
    private Long id;
    private String name;
    private String description;
    private List<UserDto> users;

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}