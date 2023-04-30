package com.hildi.propm.repository;

import com.hildi.propm.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Role Repository Tests")
class RoleRepositoryTest {

    @Mock
    private RoleRepository roleRepository;

    private Role role;

    @BeforeEach
    void setUp() {
        role = Role.builder()
                .id(1L)
                .name("ADMIN")
                .build();
    }

    @Test
    @DisplayName("Save Role Test")
    void testSaveRole() {
        // configure the mock to return the saved role
        when(roleRepository.save(role)).thenReturn(role);

        // save the role
        Role savedRole = roleRepository.save(role);

        // verify that the role was saved
        assertThat(savedRole).isNotNull();
        assertThat(savedRole.getId()).isNotNull();
        assertThat(savedRole.getName()).isEqualTo(role.getName());

        // verify that the save method was called once
        verify(roleRepository).save(role);
    }

    @Test
    @DisplayName("Find Role By Id Test")
    void testFindRoleById() {
        // configure the mock to return the role
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        // find the role
        Optional<Role> foundRole = roleRepository.findById(1L);

        // verify that the role was found
        assertTrue(foundRole.isPresent());
        assertThat(foundRole.get()).isEqualTo(role);

        // verify that the findById method was called once
        verify(roleRepository).findById(1L);
    }

    @Test
    @DisplayName("Find All Roles Test")
    void testFindAllRoles() {
        // configure the mock to return a list of roles
        List<Role> roles = List.of(role);
        when(roleRepository.findAll()).thenReturn(roles);

        // find all roles
        List<Role> foundRoles = roleRepository.findAll();

        // verify that the roles were found
        assertThat(foundRoles).isEqualTo(roles);

        // verify that the findAll method was called once
        verify(roleRepository).findAll();
    }

    @Test
    @DisplayName("Delete Role By Id Test")
    void testDeleteRoleById() {
        // configure the mock to return the saved role
        when(roleRepository.save(role)).thenReturn(role);

        // save the role
        Role savedRole = roleRepository.save(role);

        // delete the role
        roleRepository.deleteById(1L);

        // verify that the role was deleted
        Optional<Role> deletedRole = roleRepository.findById(1L);
        assertFalse(deletedRole.isPresent(), "Expected role to be deleted but it still exists");

        // verify that the deleteById method was called once
        verify(roleRepository).deleteById(1L);
    }
}
