package com.eliotfgn.studentapplicationbackend.servicesTests;

import com.eliotfgn.studentapplicationbackend.dto.UniversityDto;
import com.eliotfgn.studentapplicationbackend.mappers.UniversityMapper;
import com.eliotfgn.studentapplicationbackend.models.application.University;
import com.eliotfgn.studentapplicationbackend.repositories.UniversityRepository;
import com.eliotfgn.studentapplicationbackend.services.application.UniversityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UniversityServiceTest {

    @Mock
    private UniversityRepository universityRepository;
    @Mock
    private UniversityService universityService;

    @Mock
    private UniversityMapper universityMapper;

    @Captor
    private ArgumentCaptor<University> universityDataCaptor;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        universityService =  new UniversityService(universityRepository,universityMapper);
    }

    @Test
    void testCreateUniversity(){
        final UniversityDto universityDto =  new UniversityDto(
                99L,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(20),
                "Best University");

        final University university =  new University();
        university.setName(universityDto.getName());
        university.setId(university.getId());
        university.setCreatedAt(universityDto.getCreatedAt());
        university.setUpdatedAt(universityDto.getUpdatedAt());

        Mockito.doReturn(university).when(universityMapper).mapToEntity(universityDto);
        Mockito.doReturn(university).when(universityRepository).save(any());
        Mockito.doReturn(universityDto).when(universityMapper).mapToDto(university);

        final UniversityDto createdUniversity = universityService.create(universityDto);
        Assertions.assertNotNull(createdUniversity);

        Mockito.verify(universityRepository, Mockito.atLeastOnce()).save(universityDataCaptor.capture());
        final University universityCreated = universityDataCaptor.getValue();

        Assertions.assertEquals(universityDto.getName(), universityCreated.getName());
    }

    @Test
    void testUpdateUniversity(){
        final UniversityDto universityDto =  new UniversityDto(
                100L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Existing University");

        final University university =  new University();
        university.setName(universityDto.getName());
        university.setId(universityDto.getId());

        when(universityRepository.findById(universityDto.getId())).thenReturn(Optional.of(university));
        Mockito.doReturn(university).when(universityRepository).save(any());
        Mockito.doReturn(universityDto).when(universityMapper).mapToDto(university);

        final UniversityDto universityDataToUpdate =  new UniversityDto(
                100L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Updated University");

        Mockito.doReturn(universityDataToUpdate).when(universityMapper).mapToDto(university);
        Mockito.doReturn(university).when(universityRepository).save(any());

        final UniversityDto updatedUniversity = universityService.update(universityDto.getId(), universityDataToUpdate);

        Assertions.assertEquals(universityDataToUpdate.getName(), updatedUniversity.getName());
        Assertions.assertNotEquals(universityDto.getName(), updatedUniversity.getName());
    }


    @Test
    void deleteUniversity(){
        University existingUniversity =  new University();
        existingUniversity.setName("Existing University");
        existingUniversity.setId(999L);

        Mockito.doReturn(Optional.of(existingUniversity))
                .when(universityRepository)
                .findById(existingUniversity.getId());

        Mockito.doNothing().when(universityRepository).delete(existingUniversity);
        Assertions.assertTrue(universityService.delete(existingUniversity.getId()));
        Mockito.verify(
                universityRepository, Mockito.times(1)
                ).delete(existingUniversity);
    }

}