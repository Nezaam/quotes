package com.krymov.quotes.service.impl;

import com.krymov.quotes.dao.AccountRepository;
import com.krymov.quotes.dao.QuoteRepository;
import com.krymov.quotes.dao.VoteRepository;
import com.krymov.quotes.dto.QuoteDto;
import com.krymov.quotes.dto.ResponseQuoteDto;
import com.krymov.quotes.dto.VoteDto;
import com.krymov.quotes.exception.AccountNotFoundException;
import com.krymov.quotes.exception.OperationNotAllowedException;
import com.krymov.quotes.exception.QuoteNotFoundException;
import com.krymov.quotes.model.Account;
import com.krymov.quotes.model.Quote;
import com.krymov.quotes.model.Vote;
import com.krymov.quotes.model.VotingKey;
import com.krymov.quotes.model.enums.EVote;
import com.krymov.quotes.service.QuoteService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl implements QuoteService {

  private final QuoteRepository quoteRepository;
  private final AccountRepository accountRepository;
  private final VoteRepository voteRepository;

  private final ModelMapper mapper;

  public QuoteServiceImpl(QuoteRepository quoteRepository,AccountRepository accountRepository, VoteRepository voteRepository, ModelMapper mapper) {
    this.quoteRepository = quoteRepository;
    this.accountRepository = accountRepository;
    this.voteRepository = voteRepository;
    this.mapper = mapper;
  }

  @Override
  public ResponseQuoteDto createQuote(QuoteDto createQuoteDto) {
    Long accountId = createQuoteDto.getAccountId();

    Account account = accountRepository
        .findById(accountId)
        .orElseThrow(() -> new AccountNotFoundException(accountId));

    Quote quote = mapper.map(createQuoteDto, Quote.class);
    quote.setAccount(account);

    Quote savedQuote = quoteRepository.save(quote);

    return mapper.map(savedQuote, ResponseQuoteDto.class);
  }

  @Override
  public ResponseQuoteDto getQuoteById(Long id) {

    Quote quote = quoteRepository
        .findById(id)
        .orElseThrow(() -> new QuoteNotFoundException(id));

    return mapper.map(quote, ResponseQuoteDto.class);
  }

  @Override
  public List<ResponseQuoteDto> getAllQuotes() {

    List<Quote> quotes = quoteRepository.findAll();

    List<ResponseQuoteDto> quoteDtos = quotes
        .stream()
        .map(qu -> mapper.map(qu, ResponseQuoteDto.class))
        .collect(Collectors.toList());

    return quoteDtos;
  }

  @Override
  public ResponseQuoteDto updateQuote(Long id, QuoteDto quoteDto) {
    Long accountId = quoteDto.getAccountId();

    Quote quote = quoteRepository
        .findById(id)
        .orElseThrow(() -> new QuoteNotFoundException(id));

    if (quote.getAccount().getId() != accountId) {
      throw new OperationNotAllowedException("You cannot edit this object");
    }

    quote.setText(quoteDto.getText());

    Quote updatedQuote = quoteRepository.save(quote);

    return mapper.map(updatedQuote, ResponseQuoteDto.class);
  }

  @Override
  public void deleteQuoteById(Long id) {

    Quote quote = quoteRepository
        .findById(id)
        .orElseThrow(() -> new QuoteNotFoundException(id));

    quoteRepository.delete(quote);
  }

  @Override
  public ResponseQuoteDto upvote(VoteDto voteDto) {
    Quote quote;
    Quote updatedQuote;

    Long accountId = voteDto.getAccountId();
    Long quoteId = voteDto.getQuoteId();

    VotingKey votingKey = new VotingKey(accountId, quoteId);

    Optional<Vote> maybeVote = voteRepository.findById(votingKey);
    if (maybeVote.isPresent()) {
      Vote vote = maybeVote.get();
      quote = quoteRepository.getById(quoteId);
      if (EVote.UPVOTE == vote.getVote()) {

        int score = quote.getScore() - 1;
        quote.setScore(score);

        voteRepository.delete(vote);

      } else {

        int score = quote.getScore() - 2;
        quote.setScore(score);

        vote.setVote(EVote.DOWNVOTE);
        voteRepository.save(vote);

      }
    } else {

      Account account = accountRepository
          .findById(accountId)
          .orElseThrow(() -> new AccountNotFoundException(accountId));

      quote = quoteRepository
          .findById(quoteId)
          .orElseThrow(() -> new QuoteNotFoundException(quoteId));

      int score = quote.getScore() + 1;
      quote.setScore(score);

      Vote vote = new Vote();
      vote.setId(votingKey);
      vote.setAccount(account);
      vote.setQuote(quote);
      vote.setVote(EVote.UPVOTE);
      voteRepository.save(vote);
    }

    updatedQuote = quoteRepository.save(quote);

    return mapper.map(updatedQuote, ResponseQuoteDto.class);
  }

  @Override
  public ResponseQuoteDto downvote(VoteDto voteDto) {
    Quote quote;
    Quote updatedQuote;

    Long accountId = voteDto.getAccountId();
    Long quoteId = voteDto.getQuoteId();

    VotingKey votingKey = new VotingKey(accountId, quoteId);

    Optional<Vote> maybeVote = voteRepository.findById(votingKey);
    if (maybeVote.isPresent()) {
      Vote vote = maybeVote.get();
      quote = quoteRepository.getById(quoteId);
      if (EVote.DOWNVOTE == vote.getVote()) {

        int score = quote.getScore() + 1;
        quote.setScore(score);

        voteRepository.delete(vote);

      } else {

        int score = quote.getScore() + 2;
        quote.setScore(score);

        vote.setVote(EVote.UPVOTE);
        voteRepository.save(vote);

      }
    } else {

      Account account = accountRepository
          .findById(accountId)
          .orElseThrow(() -> new AccountNotFoundException(accountId));

      quote = quoteRepository
          .findById(quoteId)
          .orElseThrow(() -> new QuoteNotFoundException(quoteId));

      int score = quote.getScore() - 1;
      quote.setScore(score);

      Vote vote = new Vote();
      vote.setId(votingKey);
      vote.setAccount(account);
      vote.setQuote(quote);
      vote.setVote(EVote.DOWNVOTE);
      voteRepository.save(vote);
    }

    updatedQuote = quoteRepository.save(quote);

    return mapper.map(updatedQuote, ResponseQuoteDto.class);
  }

  private List<ResponseQuoteDto> getLimitedQuote(int count, boolean asc) {
    Pageable pageable;

    if (asc) {
      pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.ASC, "score"));
    } else {
      pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "score"));
    }

    return quoteRepository
        .findAll(pageable)
        .stream()
        .map(qu -> mapper.map(qu, ResponseQuoteDto.class))
        .collect(Collectors.toList());
  }
  @Override
  public List<ResponseQuoteDto> getTopQuotes(int count) {
    return getLimitedQuote(count, false);
  }

  @Override
  public List<ResponseQuoteDto> getFlopQuotes(int count) {
    return getLimitedQuote(count, true);
  }
}
