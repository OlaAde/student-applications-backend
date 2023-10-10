package com.eliotfgn.studentapplicationbackend.advices;

import com.eliotfgn.studentapplicationbackend.dto.response.ErrorResponse;
import com.eliotfgn.studentapplicationbackend.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@ResponseBody
public class UserNotFoundExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(UserNotFoundException e, WebRequest request) {
        return new ErrorResponse(false, 404, e.getMessage(), request.getDescription(false));
    }
}
