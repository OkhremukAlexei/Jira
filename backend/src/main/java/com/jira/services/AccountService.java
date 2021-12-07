package com.jira.services;

import com.jira.models.Account;

import java.util.List;


public interface AccountService {

    List<Account> getAll();
    Account getOne(Account account);
    Account put(Account account);
    void delete(Account account);

    boolean exisitById(Long id);

    boolean existsById(Long id);
}
