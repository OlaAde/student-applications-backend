package com.eliotfgn.studentapplicationbackend.dto.response;

public record ResourceResponse<T>(Boolean success, T data) { }
