package com.eliotfgn.studentapplicationbackend.servicesTests;

import com.eliotfgn.studentapplicationbackend.dto.CreateUserDto;
import com.eliotfgn.studentapplicationbackend.dto.UserDto;
import com.eliotfgn.studentapplicationbackend.dto.request.LoginRequest;
import com.eliotfgn.studentapplicationbackend.dto.response.AuthenticationResponse;
import com.eliotfgn.studentapplicationbackend.models.user.Role;
import com.eliotfgn.studentapplicationbackend.models.user.User;
import com.eliotfgn.studentapplicationbackend.services.security.AuthService;
import com.eliotfgn.studentapplicationbackend.services.user.JwtService;
import com.eliotfgn.studentapplicationbackend.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class AuthServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private AuthService authService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService =  new AuthService(userService,
                passwordEncoder,
                authenticationManager,
                jwtService,
                userDetailsService);
    }

    @Test
    void testRegisterUser() {
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

        Mockito.doReturn("encodedPassword")
                .when(passwordEncoder)
                .encode(createUserDto.getPassword());

        Mockito.doReturn(userDto).when(userService).create(createUserDto);
        Mockito.doReturn("testToken").when(jwtService).generateToken(userDto.getEmail());

        AuthenticationResponse authenticationResponse = authService.register(createUserDto);

        assertEquals("testToken", authenticationResponse.getToken());
        assertEquals(userDto, authenticationResponse.getUser());
    }

    //Not working for the credentials
    @Test
    void testUserLogin() {
        LoginRequest loginRequest = new LoginRequest("test@example.com","password");

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(loginRequest.email())
                .password("password")
                .build();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, loginRequest.password());

        UserDto userDto =  new UserDto(
                888L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                loginRequest.email(),
                "drowssap",
                "Test",
                Role.ROLE_STUDENT);

        Mockito.doReturn(userDetails).when(userDetailsService).loadUserByUsername(loginRequest.email());
        Mockito.doReturn("testToken").when(jwtService).generateToken(userDetails);
        Mockito.doReturn(authenticationToken).when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        Mockito.doReturn(userDto).when(userService).getByEmail(loginRequest.email());
        AuthenticationResponse authenticationResponse = authService.login(loginRequest);

        assertEquals("testToken", authenticationResponse.getToken());
        assertEquals(userDto, authenticationResponse.getUser());
    }

}