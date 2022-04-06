package com.krymov.quotes.service.impl;

import com.krymov.quotes.dao.AccountRepository;
import com.krymov.quotes.dto.AccountDto;
import com.krymov.quotes.exception.AccountAlreadyExistsException;
import com.krymov.quotes.exception.AccountNotFoundException;
import com.krymov.quotes.model.Account;
import com.krymov.quotes.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final ModelMapper mapper;

  public AccountServiceImpl(AccountRepository accountRepository, ModelMapper mapper) {
    this.accountRepository = accountRepository;
    this.mapper = mapper;
  }

  @Override
  public AccountDto createAccount(AccountDto accountDto) {
    String email = accountDto.getEmail();
    Optional<Account> maybeAccount = accountRepository.findByEmail(email);
    if (maybeAccount.isPresent()) {
      throw new AccountAlreadyExistsException(String.format("Account with email[%s] already exists",email));
    }

    Account account = mapper.map(accountDto, Account.class);
    Account saved = accountRepository.save(account);

    return mapper.map(saved, AccountDto.class);
  }

  @Override
  public AccountDto getAccountById(Long id) {

    Account account = accountRepository
        .findById(id)
        .orElseThrow(() -> new AccountNotFoundException(id));

    return mapper.map(account, AccountDto.class);
  }

  @Override
  public List<AccountDto> getAllAccounts() {

    List<Account> accounts = accountRepository.findAll();
    List<AccountDto> accountDtos = accounts
        .stream()
        .map(acc -> mapper.map(acc, AccountDto.class))
        .collect(Collectors.toList());

    return accountDtos;
  }

  @Override
  public AccountDto updateAccount(Long id, AccountDto accountDto) {

    Account account = accountRepository
        .findById(id)
        .orElseThrow(() -> new AccountNotFoundException(id));

    account.setName(accountDto.getName());
    account.setEmail(accountDto.getEmail());

    Account updatedAccount = accountRepository.save(account);

    return mapper.map(updatedAccount, AccountDto.class);
  }

  @Override
  public void deleteAccountById(Long id) {

    Account account = accountRepository
        .findById(id)
        .orElseThrow(() -> new AccountNotFoundException(id));

    accountRepository.delete(account);
  }
}
