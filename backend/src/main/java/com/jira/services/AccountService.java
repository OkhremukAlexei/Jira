package com.jira.services;

import com.jira.models.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAccountList();
    Account getOne(Long id);
    Account put(Account account);
    void delete(Account account);
}
