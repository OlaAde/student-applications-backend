package com.eliotfgn.studentapplicationbackend.mappers;

import com.eliotfgn.studentapplicationbackend.dto.UniversityDto;
import com.eliotfgn.studentapplicationbackend.models.application.University;
import org.springframework.stereotype.Component;

@Component
public class UniversityMapper {
    public UniversityDto mapToDto(University university) {
        if(university == null) {
            return null;
        }

        UniversityDto dto = new UniversityDto(
                university.getId(), university.getCreatedAt(), university.getUpdatedAt(), university.getName());

        return dto;
    }
}
