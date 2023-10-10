package com.eliotfgn.studentapplicationbackend.dto.response;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
    Boolean success;
    int status;
    String message;
    String path;

    public ErrorResponse(Boolean success, int status, String message, String path) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
