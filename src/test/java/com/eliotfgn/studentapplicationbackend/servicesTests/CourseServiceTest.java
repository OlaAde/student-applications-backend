package com.eliotfgn.studentapplicationbackend.servicesTests;

import com.eliotfgn.studentapplicationbackend.dto.CourseDto;
import com.eliotfgn.studentapplicationbackend.dto.CreateCourseDto;
import com.eliotfgn.studentapplicationbackend.dto.UniversityDto;
import com.eliotfgn.studentapplicationbackend.mappers.CourseMapper;
import com.eliotfgn.studentapplicationbackend.mappers.UniversityMapper;
import com.eliotfgn.studentapplicationbackend.models.application.Course;
import com.eliotfgn.studentapplicationbackend.models.application.University;
import com.eliotfgn.studentapplicationbackend.repositories.CourseRepository;
import com.eliotfgn.studentapplicationbackend.services.application.CourseService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private UniversityService universityService;
    @Mock
    private CourseService courseService;
    @Captor
    private ArgumentCaptor<Course> courseCaptor;

    private final CourseMapper courseMapper =  new CourseMapper(new UniversityMapper());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        courseService = new CourseService(courseRepository,courseMapper, universityService);
    }

    @Test
    void  createCourseTest(){
        final CreateCourseDto createCourseDto = new CreateCourseDto();
        final Course course = new Course();
        final University university =  new University();

        course.setId(12L);
        course.setName("Test");
        university.setId(111L);
        createCourseDto.setUniversityId(university.getId());
        createCourseDto.setName(course.getName());


        Mockito.doReturn(university).when(universityService).getEntityById(createCourseDto.getUniversityId());
        Mockito.doReturn(course).when(courseRepository).save(any());

        final CourseDto serviceResult = courseService.create(createCourseDto);
        Assertions.assertNotNull(serviceResult);

        Mockito.verify(courseRepository, Mockito.atLeastOnce()).save(courseCaptor.capture());

        final Course courseSaved = courseCaptor.getValue();
        Assertions.assertNotNull(courseSaved.getCreatedAt());
        Assertions.assertNotNull(courseSaved.getUpdatedAt());
        Assertions.assertEquals(courseSaved.getUniversity(), university);
        Assertions.assertEquals(courseSaved.getName(), course.getName());
    }


    @Test
    void testUpdateCourse() {
        final CreateCourseDto createCourseDto = new CreateCourseDto();
        Long courseId = 1L;

        UniversityDto universityDto = new UniversityDto(33L,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "Updated university name");

        University university =  new University();
        university.setName("Old university Name");

        CourseDto dataToUpdate = new CourseDto(courseId,LocalDateTime.now(),LocalDateTime.now(),"Updated Course Name",universityDto);

        Course existingCourse = new Course();
        existingCourse.setId(courseId);
        existingCourse.setName("Old Course Name");
        existingCourse.setUniversity(university);

        createCourseDto.setName(existingCourse.getName());
        createCourseDto.setUniversityName(existingCourse.getUniversity().getName());
        createCourseDto.setUniversityId(universityDto.getId());

        when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse)); // Mocking CourseRepository findById method


        Mockito.doReturn(university).when(universityService).getEntityById(createCourseDto.getUniversityId());
        Mockito.doReturn(existingCourse).when(courseRepository).save(any());

        final CourseDto serviceResult = courseService.create(createCourseDto);
        Assertions.assertNotNull(serviceResult);

        Mockito.verify(courseRepository, Mockito.times(1)).save(courseCaptor.capture());

        final Course courseSaved = courseCaptor.getValue();
        Assertions.assertEquals(courseSaved.getName(),existingCourse.getName());
        Assertions.assertEquals(courseSaved.getUniversity().getName(),existingCourse.getUniversity().getName());

        CourseDto updatedCourseDto = new CourseDto(
                dataToUpdate.getId(),
                dataToUpdate.getCreatedAt(),
                dataToUpdate.getUpdatedAt(),
                dataToUpdate.getName(),
                dataToUpdate.getUniversity());


        when(courseRepository.save(any(Course.class))).thenAnswer(dataCourseToUpdate -> {
            Course updatedCourse = dataCourseToUpdate.getArgument(0);
            updatedCourse.setUpdatedAt(LocalDateTime.now());
            university.setName(updatedCourseDto.getUniversity().getName());
            return updatedCourse;
        });

        CourseDto updatedCourse = courseService.update(courseId, updatedCourseDto);

        Assertions.assertEquals(dataToUpdate.getName(), updatedCourseDto.getName());
        Assertions.assertEquals(existingCourse.getId(), updatedCourse.getId());
        Assertions.assertEquals(dataToUpdate.getUniversity().getName(),updatedCourse.getUniversity().getName());
    }


    @Test
    void testDeleteCourse() {
        Long courseId = 333L;

        Course existingCourse = new Course();
        existingCourse.setId(courseId);
        existingCourse.setName("Course to delete");

        Mockito.when(courseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse));

        Mockito.doNothing().when(courseRepository).delete(existingCourse);

        Assertions.assertTrue(courseService.delete(courseId));

        Mockito.verify(courseRepository, Mockito.times(1)).delete(existingCourse);
    }

}