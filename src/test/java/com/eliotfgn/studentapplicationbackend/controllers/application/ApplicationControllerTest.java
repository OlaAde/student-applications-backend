package com.eliotfgn.studentapplicationbackend.controllers.application;

import com.eliotfgn.studentapplicationbackend.dto.ApplicationDto;
import com.eliotfgn.studentapplicationbackend.dto.CreateApplicationDto;
import com.eliotfgn.studentapplicationbackend.dto.response.ResourceResponse;
import com.eliotfgn.studentapplicationbackend.services.application.ApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

class ApplicationControllerTest {

    @Mock
    private ApplicationService applicationService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ApplicationController(applicationService)).build();
    }

    @Test
    void testCreateApplication() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        final ApplicationDto applicationDtoResponse = new ApplicationDto(
                1L,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(24),
                null,
                null
        );

        final CreateApplicationDto createApplicationDto = new CreateApplicationDto();
        createApplicationDto.setUserId(1L);
        createApplicationDto.setCourseId(1L);

        final ResourceResponse<ApplicationDto> resourceResponse = new ResourceResponse<>(true, applicationDtoResponse);

        Mockito.doReturn(applicationDtoResponse).when(applicationService).create(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/applications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createApplicationDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(resourceResponse)));
    }
}