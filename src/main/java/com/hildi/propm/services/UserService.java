package com.hildi.propm.services;

import com.hildi.propm.model.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface UserService {
    List<UserDto> getAllUsers();

    Optional<UserDto> getUserById(Long id);

    UserDto createUser(UserDto userDto);

    Optional<UserDto> updateUser(Long id, UserDto userDto);

    boolean deleteUser(Long id);
}


