package com.eliotfgn.studentapplicationbackend.advices;

import com.eliotfgn.studentapplicationbackend.dto.response.ErrorResponse;
import com.eliotfgn.studentapplicationbackend.exceptions.user.UserNotFoundException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class UserNotFoundExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFound(UserNotFoundException e, WebRequest request) {
        return new ErrorResponse(false, 404, e.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleJwtException(SignatureException e, WebRequest request) {
        return new ErrorResponse(false, 401, e.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleJwtMalformedException(Exception e, WebRequest request) {
        System.out.println("Handling");
        return new ErrorResponse(false, 401, e.getMessage(), request.getDescription(false));
    }
}
