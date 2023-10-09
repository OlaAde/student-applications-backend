package com.eliotfgn.studentapplicationbackend.models.application;

import com.eliotfgn.studentapplicationbackend.models.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class University extends BaseEntity {
    private String name;

    public University() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
