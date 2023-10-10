package com.eliotfgn.studentapplicationbackend.services.user;

import com.eliotfgn.studentapplicationbackend.dto.UserDto;
import com.eliotfgn.studentapplicationbackend.exceptions.user.UserNotFoundException;
import com.eliotfgn.studentapplicationbackend.mappers.UserMapper;
import com.eliotfgn.studentapplicationbackend.models.user.Role;
import com.eliotfgn.studentapplicationbackend.models.user.User;
import com.eliotfgn.studentapplicationbackend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto create(UserDto dto) {
        User user = userMapper.mapToEntity(dto);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRole(Role.ROLE_STUDENT);

        User savedUser = userRepository.save(user);

        return userMapper.mapToDto(savedUser);
    }

    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();

        return users.stream().map(userMapper::mapToDto).toList();
    }

    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        return userMapper.mapToDto(user);
    }

    public UserDto getByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));

        return userMapper.mapToDto(user);
    }

    public UserDto update(Long id, UserDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return userMapper.mapToDto(savedUser);
    }

    public boolean delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        userRepository.delete(user);

        return true;
    }
}
