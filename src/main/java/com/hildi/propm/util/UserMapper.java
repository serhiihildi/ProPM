package com.hildi.propm.util;

import com.hildi.propm.dto.UserDto;
import com.hildi.propm.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    List<UserDto> toDtoList(List<User> userList);
    List<User> toEntityList(List<UserDto> userDtoList);
}
