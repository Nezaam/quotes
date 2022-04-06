package com.krymov.quotes.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class VoteDto {

  @NotEmpty
  private Long accountId;

  @NotEmpty
  private Long quoteId;
}
