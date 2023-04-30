package com.hildi.propm.model.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RoleDto class tests")
class RoleDtoTest {

    @Test
    @DisplayName("RoleDto getters test")
    void gettersTest() {
        RoleDto roleDto = new RoleDto(1L, "roleName", "roleDescription", null);

        assertEquals(1L, roleDto.getId());
        assertEquals("roleName", roleDto.getName());
        assertEquals("roleDescription", roleDto.getDescription());
        assertNull(roleDto.getUsers());
    }

    @Test
    @DisplayName("Test no args constructor")
    void testNoArgsConstructor() {
        RoleDto roleDto = new RoleDto();
        assertNull(roleDto.getId());
        assertNull(roleDto.getName());
        assertNull(roleDto.getDescription());
        assertNull(roleDto.getUsers());
    }

    @Test
    @DisplayName("RoleDto toBuilder test")
    void toBuilderTest() {
        RoleDto originalRoleDto = new RoleDto(1L, "roleName", "roleDescription", null);
        RoleDto copiedRoleDto = originalRoleDto.toBuilder().build();

        assertEquals(originalRoleDto, copiedRoleDto);
    }

    @Test
    @DisplayName("RoleDto equals test")
    void equalsTest() {
        RoleDto roleDto1 = new RoleDto(1L, "roleName", "roleDescription", null);
        RoleDto roleDto2 = new RoleDto(1L, "roleName", "roleDescription", null);
        RoleDto roleDto3 = new RoleDto(2L, "roleName2", "roleDescription2", null);

        assertEquals(roleDto1, roleDto2);
        assertNotEquals(roleDto1, roleDto3);
    }

    @Test
    @DisplayName("RoleDto hashcode test")
    void hashCodeTest() {
        RoleDto roleDto1 = new RoleDto(1L, "roleName", "roleDescription", null);
        RoleDto roleDto2 = new RoleDto(1L, "roleName", "roleDescription", null);

        assertEquals(roleDto1.hashCode(), roleDto2.hashCode());
    }
}
