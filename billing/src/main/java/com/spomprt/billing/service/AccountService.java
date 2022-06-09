package com.spomprt.billing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spomprt.billing.aggregate.Account;
import com.spomprt.billing.model.AccountDto;
import com.spomprt.billing.model.CreateAccountDto;
import com.spomprt.billing.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final ObjectMapper objectMapper;
    private final AccountRepository accountRepository;

    public void create(CreateAccountDto createAccountDto) {
        Account account = objectMapper.convertValue(createAccountDto, Account.class);

        accountRepository.save(account);
    }

    public AccountDto get(String username) {
        return objectMapper.convertValue(
                accountRepository.findById(username).orElseThrow(EntityNotFoundException::new),
                AccountDto.class
        );
    }

    public void withdraw(String username, Long money) {
        Account account = accountRepository.findById(username).orElseThrow(EntityNotFoundException::new);

        account.withdraw(money);

        accountRepository.save(account);
    }

    public void put(String username, Long money) {
        Account account = accountRepository.findById(username).orElseThrow(EntityNotFoundException::new);

        account.putToBank(money);

        accountRepository.save(account);
    }
}
