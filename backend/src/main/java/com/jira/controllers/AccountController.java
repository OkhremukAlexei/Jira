package com.jira.controllers;

import com.jira.models.Account;
import com.jira.repos.AccountRepo;
import com.jira.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final AccountRepo accountRepo;

    public AccountController(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Autowired
    @Qualifier("AccountServiceImpl")
    private AccountService accountService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Account> getOne(@PathVariable("id") Account account) {
        return ResponseEntity.ok(account);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Account> put(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.put(account));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Account account) {
        accountService.delete(account);
    }
}
