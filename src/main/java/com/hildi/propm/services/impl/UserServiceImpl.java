package com.hildi.propm.services.impl;

import com.hildi.propm.model.User;
import com.hildi.propm.model.dto.UserDto;
import com.hildi.propm.repository.UserRepository;
import com.hildi.propm.services.UserService;
import com.hildi.propm.util.mapper.CustomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomMapper mapper;

    public UserServiceImpl(UserRepository userRepository, CustomMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("Get all users, count: {}", users.size());
        return mapper.map(users, UserDto.class);
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            log.info("Get user by id: {}", id);
            return Optional.of(mapper.map(user, UserDto.class));
        } else {
            log.warn("User with id {} not found", id);
            return Optional.empty();
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user = userRepository.save(user);
        log.info("Create user with id: {}", user.getId());
        return mapper.map(user, UserDto.class);
    }

    @Override
    public Optional<UserDto> updateUser(Long id, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = mapper.map(userDto, User.class);
            user.setId(id);
            user = userRepository.save(user);
            log.info("Update user with id: {}", user.getId());
            return Optional.of(mapper.map(user, UserDto.class));
        } else {
            log.warn("User with id {} not found", id);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            log.info("Delete user with id: {}", id);
            return true;
        } else {
            log.warn("User with id {} not found", id);
            return false;
        }
    }

}

