package com.krymov.quotes.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class QuoteDto {

    private Long id;

    @NotEmpty
    private String text;

    private Long accountId;
}
