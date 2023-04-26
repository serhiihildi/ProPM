package com.hildi.propm.model;

import com.hildi.propm.dto.RoleDto;
import com.hildi.propm.repository.RoleRepository;
import com.hildi.propm.services.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class RoleTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private RoleDto roleDto;
    private Role role;

    @BeforeEach
    public void setUp() {
        roleDto = new RoleDto(1L, "ADMIN");
        role = new Role(1L, "ADMIN");
    }

    @Test
    void testGetRoleById() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        RoleDto found = roleService.getRoleById(1L);

        assertEquals(roleDto.getId(), found.getId());
        assertEquals(roleDto.getName(), found.getName());
    }

    @Test
    void testGetAllRoles() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        when(roleRepository.findAll()).thenReturn(roleList);

        List<RoleDto> found = roleService.getAllRoles();

        assertEquals(1, found.size());
        assertEquals(roleDto.getId(), found.get(0).getId());
        assertEquals(roleDto.getName(), found.get(0).getName());
    }

    @Test
    void testCreateRole() {
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        RoleDto created = roleService.createRole(roleDto);

        assertEquals(roleDto.getId(), created.getId());
        assertEquals(roleDto.getName(), created.getName());
    }

    @Test
    void testUpdateRole() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        RoleDto updated = roleService.updateRole(1L, roleDto);

        assertEquals(roleDto.getId(), updated.getId());
        assertEquals(roleDto.getName(), updated.getName());
    }

    @Test
    void testDeleteRole() {
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        roleService.deleteRole(1L);

        verify(roleRepository, times(1)).deleteById(1L);
    }
}
