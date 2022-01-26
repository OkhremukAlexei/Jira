package com.jira.controllers;

import com.jira.models.Account;
import com.jira.pojo.dto.AccountDto;
import com.jira.repos.AccountRepo;
import com.jira.services.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    @Autowired
    @Qualifier("AccountServiceImpl")
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Account>> getAll() {
        final List <Account> accounts =  accountService.getAccountList();
        return accounts!=null && !accounts.isEmpty()
                ? new ResponseEntity<>(accounts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Account getOne(@PathVariable("id") Account account) {
        return account;
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Account put(@RequestBody Account account) {
        return accountService.put(account);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Account account) {
        accountService.delete(account);
    }

    private AccountDto convertToDto (Account account){
        AccountDto accountDto = modelMapper.map(account,AccountDto.class);



        return accountDto;
    }

}
