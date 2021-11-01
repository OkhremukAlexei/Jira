package com.jira.controllers;

import com.jira.models.Account;
import com.jira.repos.AccountRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final AccountRepo accountRepo;

    public AccountController(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Iterable<Account> getAll() {
        return accountRepo.findAll();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Account getOne(@PathVariable("id") Account account) {
        return account;
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Account put(@RequestBody Account account) {
        return accountRepo.save(account);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Account account) {
        accountRepo.delete(account);
    }
}
