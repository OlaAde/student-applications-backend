package com.eliotfgn.studentapplicationbackend.models.application;

import com.eliotfgn.studentapplicationbackend.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Course extends BaseEntity {
    private String name;
    @ManyToOne
    private University university;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
