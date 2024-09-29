package com.interceptor.example.user.domain.errors;

public class ErrorCreateException extends Exception{

  public ErrorCreateException(String message){
    super(message);
  }
}
