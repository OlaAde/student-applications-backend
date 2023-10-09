package com.eliotfgn.studentapplicationbackend.models.application;

import com.eliotfgn.studentapplicationbackend.models.BaseEntity;
import com.eliotfgn.studentapplicationbackend.models.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Application extends BaseEntity {
    @ManyToOne
    User user;
    @OneToOne
    Course course;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
