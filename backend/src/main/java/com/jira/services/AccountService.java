package com.jira.services;

import com.jira.models.Account;


public interface AccountService {

    Iterable<Account> getAll();
    Account getOne(Account account);
    Account put(Account account);
    void delete(Account account);
}
