package com.krymov.quotes.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotingKey implements Serializable {

  @Column(name = "account_id")
  Long accountId;

  @Column(name = "quote_id")
  Long quoteId;
}
