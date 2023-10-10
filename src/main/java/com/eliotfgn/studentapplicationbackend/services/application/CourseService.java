package com.eliotfgn.studentapplicationbackend.services.application;

import com.eliotfgn.studentapplicationbackend.dto.CourseDto;
import com.eliotfgn.studentapplicationbackend.dto.CreateCourseDto;
import com.eliotfgn.studentapplicationbackend.mappers.CourseMapper;
import com.eliotfgn.studentapplicationbackend.models.application.Course;
import com.eliotfgn.studentapplicationbackend.models.application.University;
import com.eliotfgn.studentapplicationbackend.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final UniversityService universityService;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper, UniversityService universityService) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.universityService = universityService;
    }

    public CourseDto create(CreateCourseDto dto) {
        Course course = new Course();
        course.setName(dto.getName());

        course.setUniversity(universityService.getEntityById(dto.getUniversityId()));
        course.setCreatedAt(LocalDateTime.now());
        course.setUpdatedAt(LocalDateTime.now());

        Course savedCourse = courseRepository.save(course);

        universityService.addCourse(dto.getUniversityId(), savedCourse);

        return courseMapper.mapToDto(savedCourse);
    }

    public List<CourseDto> getAll() {
        List<Course> courses = courseRepository.findAll();

        return courses.stream().map(courseMapper::mapToDto).toList();
    }

    public List<CourseDto> getAllByUniversityId(Long id) {
        University university = universityService.getEntityById(id);

        List<Course> courses = courseRepository.findAllByUniversity(university);

        return courses.stream().map(courseMapper::mapToDto).toList();
    }

    public CourseDto getById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course with id " + id + " not found"));

        return courseMapper.mapToDto(course);
    }

    public Course getEntityById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course with id " + id + " not found"));

        return course;
    }

    public CourseDto update(Long id, CourseDto dto) {
        Course course = getEntityById(id);

        course.setName(dto.getName());
        course.setUpdatedAt(LocalDateTime.now());

        Course savedCourse = courseRepository.save(course);

        return courseMapper.mapToDto(savedCourse);
    }

    public boolean delete(Long id) {
        Course course = getEntityById(id);

        courseRepository.delete(course);

        return true;
    }
}
