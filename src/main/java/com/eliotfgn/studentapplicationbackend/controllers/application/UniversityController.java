package com.eliotfgn.studentapplicationbackend.controllers.application;

import com.eliotfgn.studentapplicationbackend.dto.UniversityDto;
import com.eliotfgn.studentapplicationbackend.dto.response.ResourceResponse;
import com.eliotfgn.studentapplicationbackend.services.application.UniversityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/universities")
public class UniversityController {
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @PostMapping("/")
    public ResponseEntity<ResourceResponse<UniversityDto>> create(@RequestBody UniversityDto dto) {
        return ResponseEntity.ok(new ResourceResponse<>(true, universityService.create(dto)));
    }

    @GetMapping("/")
    public ResponseEntity<ResourceResponse<List<UniversityDto>>> getAll() {
        return ResponseEntity.ok(new ResourceResponse<>(true, universityService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceResponse<UniversityDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ResourceResponse<>(true, universityService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceResponse<UniversityDto>> update(@PathVariable Long id, @RequestBody UniversityDto dto) {
        return ResponseEntity.ok(new ResourceResponse<>(true, universityService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResourceResponse<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(new ResourceResponse<>(true, "University with id " + id + " deleted successfully"));
    }
}
