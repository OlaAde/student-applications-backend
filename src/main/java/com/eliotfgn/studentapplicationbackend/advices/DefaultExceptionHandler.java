package com.eliotfgn.studentapplicationbackend.advices;

import com.eliotfgn.studentapplicationbackend.dto.response.ErrorResponse;
import com.eliotfgn.studentapplicationbackend.dto.response.InvalidBodyResponse;
import com.eliotfgn.studentapplicationbackend.exceptions.user.UserNotFoundException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@ResponseBody
public class DefaultExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidRequestBody(Exception e, WebRequest request) {
        return new ErrorResponse(false,
                HttpStatus.BAD_REQUEST.value(),
                "You should provide a valid request body",
                request.getDescription(false).substring(4));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidBodyResponse invalidRequestParams(MethodArgumentNotValidException e, WebRequest request) {
        return new InvalidBodyResponse(false,
                HttpStatus.BAD_REQUEST.value(),
                "You should provide valid request parameters.",
                request.getDescription(false).substring(4),
                e.getFieldErrors()
                        .stream()
                        .map(error -> Map.entry(error.getField(), error.getDefaultMessage()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
        );
    }

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
