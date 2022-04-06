package com.krymov.quotes.service;

import com.krymov.quotes.dto.AccountDto;

import java.util.List;

public interface AccountService {
  AccountDto createAccount(AccountDto accountDto);

  AccountDto getAccountById(Long id);

  List<AccountDto> getAllAccounts();

  AccountDto updateAccount(Long id, AccountDto accountDto);

  void deleteAccountById(Long id);
}
