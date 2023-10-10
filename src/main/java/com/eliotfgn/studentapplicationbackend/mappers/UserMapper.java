package com.eliotfgn.studentapplicationbackend.mappers;

import com.eliotfgn.studentapplicationbackend.dto.UserDto;
import com.eliotfgn.studentapplicationbackend.models.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto mapToDto(User entity) {
        UserDto dto = new UserDto(entity.getId(), entity.getCreatedAt(), entity.getUpdatedAt(), entity.getEmail(), entity.getPassword(), entity.getFirstname(), entity.getLastname(), entity.getRole());

        return dto;
    }

    public User mapToEntity(UserDto dto) {
        User user = new User();

        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());

        return user;
    }
}
