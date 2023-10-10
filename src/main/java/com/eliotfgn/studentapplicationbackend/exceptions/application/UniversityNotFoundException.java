package com.eliotfgn.studentapplicationbackend.exceptions.application;

public class UniversityNotFoundException  extends RuntimeException {
    public UniversityNotFoundException(String message) {
        super(message);
    }
}
