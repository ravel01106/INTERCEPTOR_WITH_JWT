package com.interceptor.example.user.domain.models;

import com.interceptor.example.user.domain.types.ErrorType;

public class ErrorMessage {
  private String message;
  private ErrorType errorType;

  public ErrorMessage() {
  }

  public ErrorMessage(String message, ErrorType errorType) {
    this.message = message;
    this.errorType = errorType;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ErrorType getErrorType() {
    return errorType;
  }

  public void setErrorType(ErrorType errorType) {
    this.errorType = errorType;
  }

  @Override
  public String toString() {
    return "ErrorMessage [message=" + message + ", errorType=" + errorType + "]";
  }

}
