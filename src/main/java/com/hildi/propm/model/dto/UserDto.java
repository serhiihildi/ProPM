package com.hildi.propm.model.dto;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleDto> roles;
}
