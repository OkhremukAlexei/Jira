package com.jira.services.impl;

import com.jira.models.Account;
import com.jira.models.User;
import com.jira.pojo.dto.AccountDto;
import com.jira.pojo.dto.UserDto;
import com.jira.repos.AccountRepo;
import com.jira.services.AccountService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public List<Account> getAccountList() {
        List <Account> accounts = accountRepo.findAll();
        return accounts;
    }

    @Override
    public Account getOne(Long id) {
        Account account = accountRepo.findById(id)
                .orElseThrow(() -> new ServiceException("Account Not Found with id: " + id));;
        return account;
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
