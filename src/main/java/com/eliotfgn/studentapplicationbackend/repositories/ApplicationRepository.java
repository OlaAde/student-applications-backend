package com.eliotfgn.studentapplicationbackend.repositories;

import com.eliotfgn.studentapplicationbackend.models.application.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
