package com.krymov.quotes.dto;

public class ErrorDto {
  private String message;
  private String details;

  public ErrorDto(String message, String details) {
    this.message = message;
    this.details = details;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }
}