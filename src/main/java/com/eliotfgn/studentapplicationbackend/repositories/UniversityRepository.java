package com.eliotfgn.studentapplicationbackend.repositories;

import com.eliotfgn.studentapplicationbackend.models.application.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {
}
