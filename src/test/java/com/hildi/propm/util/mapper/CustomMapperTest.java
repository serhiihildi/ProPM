package com.hildi.propm.util.mapper;

import com.hildi.propm.model.Role;
import com.hildi.propm.model.User;
import com.hildi.propm.model.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CustomMapperTest {

    @InjectMocks
    private CustomMapper customMapper;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@gmail.com")
                .password("password")
                .roles(new HashSet<>(Collections.singletonList(Role.ROLE_USER)))
                .build();

        // Создаем дто из юзера
        userDto = UserDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@gmail.com")
                .password("password")
                .roles(new HashSet<>(Collections.singletonList(Role.ROLE_USER)))
                .build();
    }

    @Test
    @DisplayName("Test map from entity to dto")
    void testMapEntityToDto() {
        UserDto result = customMapper.map(user, UserDto.class);

        assertEquals(userDto, result);
    }

    @Test
    @DisplayName("Test map from dto to entity")
    void testMapDtoToEntity() {
        User result = customMapper.map(userDto, User.class);

        assertEquals(user, result);
    }

    @Test
    @DisplayName("Test map list of entities to list of dtos")
    void testMapListOfEntitiesToListOfDtos() {
        List<User> userList = Collections.singletonList(user);
        List<UserDto> userDtoList = Collections.singletonList(userDto);

        List<UserDto> result = customMapper.map(userList, UserDto.class);

        assertEquals(userDtoList, result);
    }

    @Test
    @DisplayName("Test map set of entities to set of dtos")
    void testMapSetOfEntitiesToSetOfDtos() {
        HashSet<User> userSet = new HashSet<>(Collections.singletonList(user));
        HashSet<UserDto> userDtoSet = new HashSet<>(Collections.singletonList(userDto));

        HashSet<UserDto> result = customMapper.map(userSet, UserDto.class);

        assertEquals(userDtoSet, result);
    }
}
