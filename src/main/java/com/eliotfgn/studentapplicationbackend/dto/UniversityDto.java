package com.eliotfgn.studentapplicationbackend.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.eliotfgn.studentapplicationbackend.models.application.University}
 */
public class UniversityDto implements Serializable {
    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    @NotBlank(message = "The university name should be given.")
    private final String name;
    private final List<CourseDto> courses;

    public UniversityDto(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String name, List<CourseDto> courses) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public List<CourseDto> getCourses() {
        return courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityDto entity = (UniversityDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.updatedAt, entity.updatedAt) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.courses, entity.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, name, courses);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "createdAt = " + createdAt + ", " +
                "updatedAt = " + updatedAt + ", " +
                "name = " + name + ", " +
                "courses = " + courses + ")";
    }
}