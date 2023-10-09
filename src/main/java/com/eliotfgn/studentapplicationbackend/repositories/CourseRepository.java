package com.eliotfgn.studentapplicationbackend.repositories;

import com.eliotfgn.studentapplicationbackend.models.application.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
