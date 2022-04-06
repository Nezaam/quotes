package com.krymov.quotes.controller;

import com.krymov.quotes.dto.AccountDto;
import com.krymov.quotes.dto.ResponseQuoteDto;
import com.krymov.quotes.service.AccountService;
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
@RequestMapping(path = "/api/v1/accounts")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @Operation(summary = "Get all accounts")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of accounts",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) })
  })
  @GetMapping(path = "/")
  public ResponseEntity<List<AccountDto>> getAllAccounts() {
    return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
  }

  @Operation(summary = "Get account by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found account",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) }),
      @ApiResponse(responseCode = "404", description = "Account not found",
          content = @Content)})
  @GetMapping(path = "/{id}")
  public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
    return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
  }

  @Operation(summary = "Create account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Created account",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Account already exists",
          content = @Content)
  })
  @PostMapping(path = "/")
  public ResponseEntity<AccountDto> createAccount(@Valid @RequestBody AccountDto accountDto) {
    return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
  }

  @Operation(summary = "Update account by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Updated account",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = ResponseQuoteDto.class)) }),
      @ApiResponse(responseCode = "404", description = "Account not found",
          content = @Content)})
  @PutMapping(path = "/{id}")
  public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @Valid @RequestBody AccountDto accountDto) {
    return new ResponseEntity<>(accountService.updateAccount(id, accountDto), HttpStatus.OK);
  }

  @Operation(summary = "Delete account by id")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Updated account",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Account not found",
          content = @Content)})
  @DeleteMapping(path = "/{id}")
  public void deleteAccount(@PathVariable Long id) {
    accountService.deleteAccountById(id);
  }
}
