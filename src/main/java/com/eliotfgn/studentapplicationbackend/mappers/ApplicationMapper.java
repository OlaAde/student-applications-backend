package com.eliotfgn.studentapplicationbackend.mappers;

import com.eliotfgn.studentapplicationbackend.dto.ApplicationDto;
import com.eliotfgn.studentapplicationbackend.models.application.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    private final UserMapper userMapper;
    private final CourseMapper courseMapper;

    public ApplicationMapper(UserMapper userMapper, CourseMapper courseMapper) {
        this.userMapper = userMapper;
        this.courseMapper = courseMapper;
    }

    public ApplicationDto mapToDto(Application entity) {
        ApplicationDto dto = new ApplicationDto(entity.getId(), entity.getCreatedAt(), entity.getUpdatedAt(), userMapper.mapToDto(entity.getUser()), courseMapper.mapToDto(entity.getCourse()));

        return dto;
    }
}
