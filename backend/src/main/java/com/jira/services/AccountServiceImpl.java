package com.jira.services;

import com.jira.models.Account;
import com.jira.repos.AccountRepo;
import org.springframework.stereotype.Service;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService{

    private final AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Iterable<Account> getAll() {
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
}
