package com.eliotfgn.studentapplicationbackend.models.application;

import com.eliotfgn.studentapplicationbackend.models.BaseEntity;
import com.eliotfgn.studentapplicationbackend.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Application extends BaseEntity {
    @ManyToOne
    User user;
}
