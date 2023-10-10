package com.eliotfgn.studentapplicationbackend.dto.response;

import com.eliotfgn.studentapplicationbackend.dto.UserDto;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    private String token;
    private UserDto user;

    public AuthenticationResponse(String token, UserDto user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}

