package com.eliotfgn.studentapplicationbackend.dto.response;

import java.util.Map;

public class InvalidBodyResponse extends ErrorResponse{
    Map<String, String> errors;
    public InvalidBodyResponse(Boolean success, int status, String message, String path, Map<String, String> errors) {
        super(success, status, message, path);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}

