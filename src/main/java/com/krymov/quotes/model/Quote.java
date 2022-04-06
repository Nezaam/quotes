package com.krymov.quotes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "quote")
@Getter
@Setter
@NoArgsConstructor
public class Quote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "score")
  private int score;

  @Column(name = "text")
  private String text;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="account_id", nullable = false)
  private Account account;

  @OneToMany(mappedBy = "quote")
  private Set<Vote> votes;
}
