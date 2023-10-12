package com.eliotfgn.studentapplicationbackend.controllers.application;

import com.eliotfgn.studentapplicationbackend.dto.CourseDto;
import com.eliotfgn.studentapplicationbackend.dto.CreateCourseDto;
import com.eliotfgn.studentapplicationbackend.dto.response.ResourceResponse;
import com.eliotfgn.studentapplicationbackend.services.application.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses/")
    public ResponseEntity<ResourceResponse<CourseDto>> create(@RequestBody CreateCourseDto dto) {
        return ResponseEntity.ok(new ResourceResponse<>(true, courseService.create(dto)));
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<ResourceResponse<CourseDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ResourceResponse<>(true, courseService.getById(id)));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<ResourceResponse<CourseDto>> update(@PathVariable Long id, @RequestBody CourseDto dto) {
        return ResponseEntity.ok(new ResourceResponse<>(true, courseService.update(id, dto)));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<ResourceResponse<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(new ResourceResponse<>(true, "Course with id " + id + " deleted successfully"));
    }

    @GetMapping("/courses/")
    public ResponseEntity<ResourceResponse<List<CourseDto>>> getAll() {
        return ResponseEntity.ok(new ResourceResponse<>(true, courseService.getAll()));
    }

    @GetMapping("/university/{id}/courses")
    public ResponseEntity<ResourceResponse<List<CourseDto>>> getAllByUniversityId(@PathVariable Long id) {
        System.out.println("Getting request");
        return ResponseEntity.ok(new ResourceResponse<>(true, courseService.getAllByUniversityId(id)));
    }
}
