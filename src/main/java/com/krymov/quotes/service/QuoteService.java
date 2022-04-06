package com.krymov.quotes.service;

import com.krymov.quotes.dto.QuoteDto;
import com.krymov.quotes.dto.ResponseQuoteDto;
import com.krymov.quotes.dto.VoteDto;

import java.util.List;

public interface QuoteService {
  ResponseQuoteDto createQuote(QuoteDto quoteDto);

  ResponseQuoteDto getQuoteById(Long id);

  List<ResponseQuoteDto> getAllQuotes();

  ResponseQuoteDto updateQuote(Long id, QuoteDto quoteDto);

  void deleteQuoteById(Long id);

  ResponseQuoteDto upvote(VoteDto voteDto);

  ResponseQuoteDto downvote(VoteDto voteDto);

  List<ResponseQuoteDto> getTopQuotes(int count);

  List<ResponseQuoteDto> getFlopQuotes(int count);
}
