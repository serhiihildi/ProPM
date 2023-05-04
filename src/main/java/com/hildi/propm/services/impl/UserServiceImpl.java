package com.hildi.propm.services.impl;

import com.hildi.propm.model.Role;
import com.hildi.propm.model.User;
import com.hildi.propm.model.dto.UserDto;
import com.hildi.propm.repository.UserRepository;
import com.hildi.propm.security.CustomPasswordEncoder;
import com.hildi.propm.services.UserService;
import com.hildi.propm.util.mapper.CustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomPasswordEncoder passwordEncoder;
    private final CustomMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CustomPasswordEncoder passwordEncoder, CustomMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
        return mapper.map(user, UserDto.class);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        HashSet<Role> roleHashSet = new HashSet<>(userDto.getRoles());
        user.setRoles(mapper.map(roleHashSet, Role.class));
        user = userRepository.save(user);

        return mapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return mapper.map(users, UserDto.class);
    }
}

