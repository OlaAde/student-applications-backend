package com.eliotfgn.studentapplicationbackend.controllers.user;

import com.eliotfgn.studentapplicationbackend.dto.CreateUserDto;
import com.eliotfgn.studentapplicationbackend.dto.UserDto;
import com.eliotfgn.studentapplicationbackend.dto.request.LoginRequest;
import com.eliotfgn.studentapplicationbackend.dto.response.AuthenticationResponse;
import com.eliotfgn.studentapplicationbackend.dto.response.ResourceResponse;
import com.eliotfgn.studentapplicationbackend.services.security.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResourceResponse<AuthenticationResponse>> register(@RequestBody @Valid CreateUserDto payload) {
        ResourceResponse<AuthenticationResponse> response;
        AuthenticationResponse authResponse = authService.register(payload);

        response = new ResourceResponse<AuthenticationResponse>(true, authResponse);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResourceResponse<AuthenticationResponse>> login(@RequestBody LoginRequest loginRequest) {
        ResourceResponse<AuthenticationResponse> response;
        AuthenticationResponse authenticationResponse = authService.login(loginRequest);

        response = new ResourceResponse<>(true, authenticationResponse);

        return ResponseEntity.ok().body(response);
    }
}
