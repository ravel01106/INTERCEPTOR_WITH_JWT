package com.interceptor.example.user.domain.errors;

public class NotFoundException extends Exception{

  public NotFoundException(String message){
    super(message);
  }
}
