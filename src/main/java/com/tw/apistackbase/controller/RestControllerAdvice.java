package com.tw.apistackbase.controller;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus (value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse notFoundException (NotFoundException e){

        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setCode(404);
        errorResponse.setMessage(e.getMessage());

        return errorResponse;
    }

}
