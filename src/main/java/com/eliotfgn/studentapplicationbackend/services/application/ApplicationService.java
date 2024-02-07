package com.eliotfgn.studentapplicationbackend.services.application;

import com.eliotfgn.studentapplicationbackend.dto.ApplicationDto;
import com.eliotfgn.studentapplicationbackend.dto.CreateApplicationDto;
import com.eliotfgn.studentapplicationbackend.mappers.ApplicationMapper;
import com.eliotfgn.studentapplicationbackend.models.application.Application;
import com.eliotfgn.studentapplicationbackend.repositories.ApplicationRepository;
import com.eliotfgn.studentapplicationbackend.services.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final CourseService courseService;
    private final UserService userService;
    private final ApplicationMapper applicationMapper;

    public ApplicationService(ApplicationRepository applicationRepository, UniversityService universityService, CourseService courseService, UserService userService, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.courseService = courseService;
        this.userService = userService;
        this.applicationMapper = applicationMapper;
    }

    public ApplicationDto create(CreateApplicationDto dto) {
        Application application = new Application();
        application.setCourse(courseService.getEntityById(dto.getCourseId()));
        application.setUser(userService.getEntityById(dto.getUserId()));
        application.setCreatedAt(LocalDateTime.now());
        application.setUpdatedAt(LocalDateTime.now());

        Application savedApplication = applicationRepository.save(application);

        userService.addApplication(dto.getUserId(), savedApplication);

        return applicationMapper.mapToDto(savedApplication);
    }

    public ApplicationDto getById(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application with id " + id + " not found"));

        return applicationMapper.mapToDto(application);
    }

    public List<ApplicationDto> getAllByUserId(Long userId) {
        List<Application> applications = applicationRepository.findByUser_Id(userId);

        return applications.stream().map(applicationMapper::mapToDto).toList();
    }
}
