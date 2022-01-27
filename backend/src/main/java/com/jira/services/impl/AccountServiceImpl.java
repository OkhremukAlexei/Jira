package com.jira.services.impl;

import com.jira.models.Account;
import com.jira.repos.AccountRepo;
import com.jira.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER= LoggerFactory.getLogger(AdminServiceImpl.class);


    private final AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo)
    {
        this.accountRepo = accountRepo;
    }

    @Override
    public Iterable<Account> getAll() {
        LOGGER.info("AccountService method getAll");
        return accountRepo.findAll();
    }
    @Override
    public Account getOne(Account account) {
        LOGGER.info("AccountService method getOne "+account.getId()+" "+account.getName()+" "+account.getEmail()+" "+account.getSurname());

        return accountRepo.findById(account.getId()).get();
    }
    @Override
    public Account put(Account account) {
        LOGGER.info("AccountService method put "+account.getId()+" "+account.getName()+" "+account.getEmail()+" "+account.getSurname());

        return accountRepo.save(account);
    }
    @Override
    public void delete(Account account) {
        LOGGER.info("AccountService method delete "+account.getId()+" "+account.getName()+" "+account.getEmail()+" "+account.getSurname());

        accountRepo.delete(account);
    }
}
