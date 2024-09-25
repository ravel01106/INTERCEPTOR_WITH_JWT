package com.interceptor.example.user.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.interceptor.example.user.domain.errors.AlrreadyExistsException;
import com.interceptor.example.user.domain.errors.ErrorCreateException;
import com.interceptor.example.user.domain.errors.NotFoundException;
import com.interceptor.example.user.domain.models.ErrorMessage;
import com.interceptor.example.user.domain.types.ErrorType;

@ControllerAdvice
public class HandlerErrorController {


  @ExceptionHandler(AlrreadyExistsException.class)
  public ResponseEntity<ErrorMessage> handlerErrorAlreadyExists(AlrreadyExistsException ex){

    return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), ErrorType.ALREADY_EXISTS), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AlrreadyExistsException.class)
  public ResponseEntity<ErrorMessage> handlerErrorNotFound(NotFoundException ex){

    return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), ErrorType.NOT_FOUND), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AlrreadyExistsException.class)
  public ResponseEntity<ErrorMessage> handlerErrorCreate(ErrorCreateException ex){

    return new ResponseEntity<>(new ErrorMessage(ex.getMessage(), ErrorType.ERROR_CREATE), HttpStatus.BAD_REQUEST);
  }

}
