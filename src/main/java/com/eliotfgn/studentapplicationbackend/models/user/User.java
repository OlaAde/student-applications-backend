package com.eliotfgn.studentapplicationbackend.models.user;


import com.eliotfgn.studentapplicationbackend.models.BaseEntity;
import com.eliotfgn.studentapplicationbackend.models.application.Application;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class User extends BaseEntity {
    @Column(unique = true)
    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be valid.")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    @NotBlank(message = "Password is required.")
    private String password;
    private String firstname;
    private String lastname;
    @OneToMany
    private List<Application> applications;

    public User() {
    }

    public User(String email, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }
}
