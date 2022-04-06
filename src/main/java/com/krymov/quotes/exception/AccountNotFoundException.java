package com.krymov.quotes.exception;

public class AccountNotFoundException extends RuntimeException {

  public AccountNotFoundException(Long accountId) {
    super(String.format("Account with id[%s] not found", accountId));
  }

  public AccountNotFoundException() {
  }

  public AccountNotFoundException(String message) {
    super(message);
  }

  public AccountNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
