package com.eliotfgn.studentapplicationbackend.servicesTests;

import com.eliotfgn.studentapplicationbackend.dto.CreateUserDto;
import com.eliotfgn.studentapplicationbackend.dto.UserDto;
import com.eliotfgn.studentapplicationbackend.mappers.UserMapper;
import com.eliotfgn.studentapplicationbackend.models.user.Role;
import com.eliotfgn.studentapplicationbackend.models.user.User;
import com.eliotfgn.studentapplicationbackend.repositories.UserRepository;
import com.eliotfgn.studentapplicationbackend.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService =  new UserService(userRepository, userMapper);
    }

    @Test
    void testCreateUser() {
        CreateUserDto createUserDto = new CreateUserDto(
                888L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "fakeEmail@gmail.com",
                "drowssap",
                "Test",
                "TestTest",
                Role.ROLE_STUDENT
        );

        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setFirstname(createUserDto.getFirstname());
        user.setLastname(createUserDto.getLastname());
        user.setPassword(createUserDto.getPassword());
        user.setRole(createUserDto.getRole());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setApplications(new ArrayList<>());


        UserDto userDto =  new UserDto(
                user.getId(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                user.getRole());

        Mockito.doReturn(user).when(userMapper).mapToEntity(createUserDto);
        Mockito.doReturn(user).when(userRepository).save(any());
        Mockito.doReturn(userDto).when(userMapper).mapToDto(user);

        UserDto createdUser = userService.create(createUserDto);

        assertEquals(userDto.getEmail(), createdUser.getEmail());
        assertEquals(userDto.getFirstname(), createdUser.getFirstname());
        assertEquals(userDto.getLastname(), createdUser.getLastname());
    }

}