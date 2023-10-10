package com.eliotfgn.studentapplicationbackend.dto;

import java.io.Serializable;

public class CreateCourseDto implements Serializable {
    private String name;
    private Long universityId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Long universityId) {
        this.universityId = universityId;
    }
}
