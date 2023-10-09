package com.eliotfgn.studentapplicationbackend.repositories;

import com.eliotfgn.studentapplicationbackend.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
