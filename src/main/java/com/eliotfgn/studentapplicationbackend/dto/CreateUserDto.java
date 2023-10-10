package com.eliotfgn.studentapplicationbackend.dto;

import com.eliotfgn.studentapplicationbackend.models.user.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class CreateUserDto implements Serializable {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Email(message = "Email must be valid.")
    @NotBlank(message = "Email is required.")
    private String email;
    @Size(message = "Password must be at least 8 characters long.", min = 8)
    @NotBlank(message = "Password is required.")
    private String password;
    private String firstname;
    private String lastname;
    private Role role;

    public CreateUserDto(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String email, String password, String firstname, String lastname, Role role) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, email, password, firstname, lastname, role);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "createdAt = " + createdAt + ", " +
                "updatedAt = " + updatedAt + ", " +
                "email = " + email + ", " +
                "password = " + password + ", " +
                "firstname = " + firstname + ", " +
                "lastname = " + lastname + ", " +
                "role = " + role + ")";
    }
}
