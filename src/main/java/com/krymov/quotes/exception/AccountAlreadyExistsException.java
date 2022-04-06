package com.krymov.quotes.exception;

public class AccountAlreadyExistsException extends RuntimeException {
  public AccountAlreadyExistsException() {
  }

  public AccountAlreadyExistsException(String message) {
    super(message);
  }

  public AccountAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
