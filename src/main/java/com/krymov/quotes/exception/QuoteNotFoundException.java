package com.krymov.quotes.exception;

public class QuoteNotFoundException extends RuntimeException {

  public QuoteNotFoundException(Long quoteId) {
    super(String.format("Quote with id[%s] not found", quoteId));
  }
  public QuoteNotFoundException() {
  }

  public QuoteNotFoundException(String message) {
    super(message);
  }

  public QuoteNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
