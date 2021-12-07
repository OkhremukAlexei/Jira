package com.jira.services.impl;

import com.jira.models.Account;
import com.jira.repos.AccountRepo;
import com.jira.services.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public List<Account> getAll() {
        return accountRepo.findAll();
    }
    @Override
    public Account getOne(Account account) {
        return accountRepo.findById(account.getId()).get();
    }
    @Override
    public Account put(Account account) {
        return accountRepo.save(account);
    }
    @Override
    public void delete(Account account) {
        accountRepo.delete(account);
    }

    @Override
    public boolean exisitById(Long id) {
        return false;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}
