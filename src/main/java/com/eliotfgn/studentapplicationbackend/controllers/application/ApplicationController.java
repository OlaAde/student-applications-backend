package com.eliotfgn.studentapplicationbackend.controllers.application;

import com.eliotfgn.studentapplicationbackend.dto.ApplicationDto;
import com.eliotfgn.studentapplicationbackend.dto.CreateApplicationDto;
import com.eliotfgn.studentapplicationbackend.dto.response.ResourceResponse;
import com.eliotfgn.studentapplicationbackend.services.application.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/applications/")
    public ResponseEntity<ResourceResponse<ApplicationDto>> create(@RequestBody CreateApplicationDto dto) {
        return ResponseEntity.ok(new ResourceResponse<>(true, applicationService.create(dto)));
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<ResourceResponse<ApplicationDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ResourceResponse<>(true, applicationService.getById(id)));
    }

    @GetMapping("/users/{id}/applications")
    public ResponseEntity<ResourceResponse<List<ApplicationDto>>> getAllByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(new ResourceResponse<>(true, applicationService.getAllByUserId(id)));
    }

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<ResourceResponse<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(new ResourceResponse<>(true, "Application with id " + id + " deleted successfully"));
    }
}
