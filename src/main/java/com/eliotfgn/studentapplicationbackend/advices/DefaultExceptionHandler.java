package com.eliotfgn.studentapplicationbackend.advices;

import com.eliotfgn.studentapplicationbackend.dto.response.ErrorResponse;
import com.eliotfgn.studentapplicationbackend.dto.response.InvalidBodyResponse;
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
}
