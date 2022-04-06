package com.krymov.quotes.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class AccountDto {
  private Long id;

  @NotEmpty(message = "Name should not be empty")
  private String name;

  @Email
  @NotEmpty(message = "Email should not be empty")
  private String email;
}
