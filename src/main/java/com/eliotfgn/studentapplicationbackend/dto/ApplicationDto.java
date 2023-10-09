package com.eliotfgn.studentapplicationbackend.dto;

import com.eliotfgn.studentapplicationbackend.models.application.Application;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * DTO for {@link Application}
 */
public class ApplicationDto implements Serializable {
    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final UserDto user;
    private final CourseDto course;

    public ApplicationDto(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, UserDto user, CourseDto course) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.course = course;
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

    public UserDto getUser() {
        return user;
    }

    public CourseDto getCourse() {
        return course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationDto entity = (ApplicationDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.updatedAt, entity.updatedAt) &&
                Objects.equals(this.user, entity.user) &&
                Objects.equals(this.course, entity.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, user, course);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "createdAt = " + createdAt + ", " +
                "updatedAt = " + updatedAt + ", " +
                "user = " + user + ", " +
                "course = " + course + ")";
    }
}