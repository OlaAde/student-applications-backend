package com.eliotfgn.studentapplicationbackend.mappers;

import com.eliotfgn.studentapplicationbackend.dto.CourseDto;
import com.eliotfgn.studentapplicationbackend.models.application.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    private final UniversityMapper universityMapper;

    public CourseMapper(UniversityMapper universityMapper) {
        this.universityMapper = universityMapper;
    }

    private CourseDto mapToDto(Course entity) {
        CourseDto dto = new CourseDto(entity.getId(), entity.getCreatedAt(), entity.getUpdatedAt(), entity.getName(), universityMapper.mapToDto(entity.getUniversity()));

        return dto;
    }
}
