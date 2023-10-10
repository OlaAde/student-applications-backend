package com.eliotfgn.studentapplicationbackend.repositories;

import com.eliotfgn.studentapplicationbackend.models.application.Course;
import com.eliotfgn.studentapplicationbackend.models.application.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByUniversity(University university);
}
