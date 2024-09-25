package com.interceptor.example.user.domain.errors;

public class AlrreadyExistsException extends Exception {
  public AlrreadyExistsException(String message){
    super(message);
  }
}
