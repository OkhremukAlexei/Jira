package com.jira.controllers;

import com.jira.models.Account;
import com.jira.repos.AccountRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final AccountRepo accountRepo;

    public AccountController(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @GetMapping
    public Iterable<Account> getAll() {
        return accountRepo.findAll();
    }

    @GetMapping("{id}")
    public Account getOne(@PathVariable("id") Account account) {
        return account;
    }

    @PutMapping
    public Account put(@RequestBody Account account) {
        return accountRepo.save(account);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Account account) {
        accountRepo.delete(account);
    }
}
