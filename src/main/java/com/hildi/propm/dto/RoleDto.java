package com.hildi.propm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
    private String description;

    public RoleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
