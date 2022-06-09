package com.spomprt.billing.controller;

import com.spomprt.billing.model.AccountDto;
import com.spomprt.billing.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("{username}")
    public AccountDto getAccount(@PathVariable("username") String username) {
        log.info("Get user by username");
        return accountService.get(username);
    }

    @PostMapping("{username}/put")
    public void put(@PathVariable("username") String username, Long money) {
        accountService.put(username, money);
    }

    @PostMapping("{username}/withdraw")
    public void withdraw(@PathVariable("username") String username, Long money) {
        accountService.withdraw(username, money);
    }

}
