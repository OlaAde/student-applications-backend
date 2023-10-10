package com.eliotfgn.studentapplicationbackend.services.security;

import com.eliotfgn.studentapplicationbackend.dto.CreateUserDto;
import com.eliotfgn.studentapplicationbackend.dto.UserDto;
import com.eliotfgn.studentapplicationbackend.dto.request.LoginRequest;
import com.eliotfgn.studentapplicationbackend.dto.response.AuthenticationResponse;
import com.eliotfgn.studentapplicationbackend.services.user.JwtService;
import com.eliotfgn.studentapplicationbackend.services.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public AuthenticationResponse register(CreateUserDto payload) {
        payload.setPassword(passwordEncoder.encode(payload.getPassword()));
        UserDto user = userService.create(payload);
        String token = jwtService.generateToken(user.getEmail());

        return new AuthenticationResponse(token, user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) throws BadCredentialsException {
        AuthenticationResponse authenticationResponse = null;
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.email());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, loginRequest.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Invalid credentials.");
        }

        String token = jwtService.generateToken(userDetails);
        UserDto userDto = userService.getByEmail(userDetails.getUsername());

        authenticationResponse = new AuthenticationResponse(token, userDto);

        return authenticationResponse;
    }
}
