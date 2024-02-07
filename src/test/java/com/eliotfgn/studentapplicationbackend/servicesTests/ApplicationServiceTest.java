package com.eliotfgn.studentapplicationbackend.servicesTests;

import com.eliotfgn.studentapplicationbackend.dto.ApplicationDto;
import com.eliotfgn.studentapplicationbackend.dto.CreateApplicationDto;
import com.eliotfgn.studentapplicationbackend.mappers.ApplicationMapper;
import com.eliotfgn.studentapplicationbackend.mappers.CourseMapper;
import com.eliotfgn.studentapplicationbackend.mappers.UniversityMapper;
import com.eliotfgn.studentapplicationbackend.mappers.UserMapper;
import com.eliotfgn.studentapplicationbackend.models.application.Application;
import com.eliotfgn.studentapplicationbackend.models.application.Course;
import com.eliotfgn.studentapplicationbackend.models.user.User;
import com.eliotfgn.studentapplicationbackend.repositories.ApplicationRepository;
import com.eliotfgn.studentapplicationbackend.services.application.ApplicationService;
import com.eliotfgn.studentapplicationbackend.services.application.CourseService;
import com.eliotfgn.studentapplicationbackend.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ApplicationServiceTest {

    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private CourseService courseService;
    @Mock
    private UserService userService;
    @Captor
    private ArgumentCaptor<Application> applicationCaptor;
    private final ApplicationMapper applicationMapper = new ApplicationMapper(
            new UserMapper(),
            new CourseMapper(new UniversityMapper())
            );
    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        applicationService =  new ApplicationService(
                applicationRepository,
                null,
                courseService,
                userService,
                applicationMapper
        );
    }

    @Test
    void testCreateApplication() {
        final CreateApplicationDto createApplicationDto = new CreateApplicationDto();
        final Course course = new Course();
        final User user = new User();
        final Application savedApplication = new Application();


        createApplicationDto.setUserId(1L);
        createApplicationDto.setCourseId(1L);

        savedApplication.setCourse(course);
        savedApplication.setUser(user);

        Mockito.doReturn(course).when(courseService).getEntityById(createApplicationDto.getCourseId());
        Mockito.doReturn(user).when(userService).getEntityById(createApplicationDto.getUserId());
        Mockito.doReturn(savedApplication).when(applicationRepository).save(Mockito.any());

        final ApplicationDto serviceResult = applicationService.create(createApplicationDto);

        Mockito.verify(applicationRepository, Mockito.times(1))
                .save(applicationCaptor.capture());

        final Application applicationToSave = applicationCaptor.getValue();
        Assertions.assertNotNull(applicationToSave.getCreatedAt());
        Assertions.assertNotNull(applicationToSave.getUpdatedAt());
        Assertions.assertEquals(course, applicationToSave.getCourse());
        Assertions.assertEquals(user, applicationToSave.getUser());
        Assertions.assertNotNull(serviceResult);
    }

}