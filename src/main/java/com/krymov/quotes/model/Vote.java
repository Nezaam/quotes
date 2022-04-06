package com.krymov.quotes.model;

import com.krymov.quotes.model.enums.EVote;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vote")
@Getter
@Setter
@NoArgsConstructor
public class Vote {

  @EmbeddedId
  VotingKey id;

  @ManyToOne
  @MapsId("accountId")
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne
  @MapsId("quoteId")
  @JoinColumn(name = "quote_id")
  private Quote quote;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "vote")
  private EVote vote;
}
