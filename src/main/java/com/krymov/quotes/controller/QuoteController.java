package com.krymov.quotes.controller;

import com.krymov.quotes.dto.QuoteDto;
import com.krymov.quotes.dto.ResponseQuoteDto;
import com.krymov.quotes.dto.VoteDto;
import com.krymov.quotes.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
public class QuoteController {

  private final QuoteService quoteService;

  public QuoteController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @Operation(summary = "Get all quotes")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of quotes",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) })
  })
  @GetMapping("/")
  ResponseEntity<List<ResponseQuoteDto>> getAllQuotes() {
    return new ResponseEntity<>(quoteService.getAllQuotes(), HttpStatus.OK);
  }

  @Operation(summary = "Get quote by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found quote",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) }),
      @ApiResponse(responseCode = "404", description = "Quote not found",
          content = @Content) })
  @GetMapping("/{id}")
  ResponseEntity<ResponseQuoteDto> getQuoteById(@PathVariable Long id) {
    return new ResponseEntity<>(quoteService.getQuoteById(id), HttpStatus.OK);
  }

  @Operation(summary = "Create quote")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Quote created",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) }),
      @ApiResponse(responseCode = "404", description = "Account not found",
          content = @Content) })
  @PostMapping("/")
  ResponseEntity<ResponseQuoteDto> createQuote(@Valid @RequestBody QuoteDto createQuoteDto) {
    return new ResponseEntity<>(quoteService.createQuote(createQuoteDto), HttpStatus.CREATED);
  }

  @Operation(summary = "Update quote by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Updated quote",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) }),
      @ApiResponse(responseCode = "403", description = "Operation not allowed",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Quote not found",
          content = @Content) })
  @PutMapping("/{id}")
  ResponseEntity<ResponseQuoteDto> updateQuote(@PathVariable Long id, @Valid @RequestBody QuoteDto quoteDto) {
    return new ResponseEntity<>(quoteService.updateQuote(id, quoteDto), HttpStatus.OK);
  }

  @Operation(summary = "Delete quote by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Quote deleted",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Quote not found",
          content = @Content) })
  @DeleteMapping("/{id}")
  void deleteQuote(@PathVariable Long id) {
      quoteService.deleteQuoteById(id);
  }

  @Operation(summary = "Upvote quote")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Upvoted quote",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) }),
      @ApiResponse(responseCode = "404", description = "Account not found",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Quote not found",
          content = @Content) })
  @PostMapping(path = "/upvote")
  public ResponseEntity<ResponseQuoteDto> upvote(@RequestBody VoteDto voteDto) {
    return new ResponseEntity<>(quoteService.upvote(voteDto), HttpStatus.OK);
  }

  @Operation(summary = "Downvote quote")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Downvoted quote",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) }),
      @ApiResponse(responseCode = "404", description = "Account not found",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Quote not found",
          content = @Content) })
  @PostMapping(path = "/downvote")
  public ResponseEntity<ResponseQuoteDto> downvote(@RequestBody VoteDto voteDto) {
    return new ResponseEntity<>(quoteService.downvote(voteDto), HttpStatus.OK);
  }

  @Operation(summary = "Get top 10 quotes")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Top 25 quotes",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) })
  })
  @GetMapping("/top")
  public ResponseEntity<List<ResponseQuoteDto>> getTopQuotes() {
    int count = 10;
    return new ResponseEntity<>(quoteService.getTopQuotes(count), HttpStatus.OK);
  }

  @Operation(summary = "Get flop 10 quotes")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Flop 25 quotes",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) })
  })
  @GetMapping("/flop")
  public ResponseEntity<List<ResponseQuoteDto>> getFlopQuotes() {
    int count = 10;
    return new ResponseEntity<>(quoteService.getFlopQuotes(count), HttpStatus.OK);
  }
}
