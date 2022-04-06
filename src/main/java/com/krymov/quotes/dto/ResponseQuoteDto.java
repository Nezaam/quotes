package com.krymov.quotes.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ResponseQuoteDto {
  private Long id;

  @NotEmpty
  private String text;

  private int score;

  private AccountDto account;
}
