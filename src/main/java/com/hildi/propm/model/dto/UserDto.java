package com.hildi.propm.model.dto;

import lombok.*;

import java.util.Set;


@Data
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleDto> roles;

    public UserDto(Long id, String firstName, String lastName, String email, String password, Set<RoleDto> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
