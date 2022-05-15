package com.ceiba.springmvc.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    protected ResponseEntity<Object> errorConflict (RuntimeException ex, WebRequest request , String bodyOfResponse, HttpStatus status) {
        return handleExceptionInternal(ex,bodyOfResponse,new HttpHeaders(), status, request);
    }

}
