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

    public University mapToEntity(UniversityDto dto) {
        if(dto == null) {
            return null;
        }

        University entity = new University();

        entity.setName(dto.getName());
        entity.setId(dto.getId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        return entity;
    }
}
