package com.krymov.quotes.exception;

public class OperationNotAllowedException extends RuntimeException {
  public OperationNotAllowedException() {
  }

  public OperationNotAllowedException(String message) {
    super(message);
  }

  public OperationNotAllowedException(String message, Throwable cause) {
    super(message, cause);
  }
}
