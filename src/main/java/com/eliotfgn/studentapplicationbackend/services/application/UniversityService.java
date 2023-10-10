package com.eliotfgn.studentapplicationbackend.services.application;

import com.eliotfgn.studentapplicationbackend.dto.UniversityDto;
import com.eliotfgn.studentapplicationbackend.exceptions.application.UniversityNotFoundException;
import com.eliotfgn.studentapplicationbackend.mappers.UniversityMapper;
import com.eliotfgn.studentapplicationbackend.models.application.University;
import com.eliotfgn.studentapplicationbackend.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;
    private final UniversityMapper universityMapper;

    public UniversityService(UniversityRepository universityRepository, UniversityMapper universityMapper) {
        this.universityRepository = universityRepository;
        this.universityMapper = universityMapper;
    }

    public UniversityDto create(UniversityDto dto) {
        University university = universityMapper.mapToEntity(dto);
        university.setCreatedAt(LocalDateTime.now());
        university.setUpdatedAt(LocalDateTime.now());

        University savedUniversity = universityRepository.save(university);

        return universityMapper.mapToDto(savedUniversity);
    }

    public UniversityDto getById(Long id) {
        University university = universityRepository.findById(id).orElseThrow(() -> new UniversityNotFoundException("University with id " + id + " not found"));

        return universityMapper.mapToDto(university);
    }

    public List<UniversityDto> getAll() {
        List<University> universities = universityRepository.findAll();

        return universities.stream().map(universityMapper::mapToDto).toList();
    }

    public UniversityDto update(Long id, UniversityDto dto) {
        University university = universityRepository.findById(id).orElseThrow(() -> new UniversityNotFoundException("University with id " + id + " not found"));

        university.setName(dto.getName());
        university.setUpdatedAt(LocalDateTime.now());

        University savedUniversity = universityRepository.save(university);

        return universityMapper.mapToDto(savedUniversity);
    }

    public boolean delete(Long id) {
        University university = universityRepository.findById(id).orElseThrow(() -> new UniversityNotFoundException("University with id " + id + " not found"));

        universityRepository.delete(university);

        return true;
    }
}
