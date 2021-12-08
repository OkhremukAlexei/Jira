package com.jira.services.impl;

import com.jira.models.Account;
import com.jira.pojo.dto.AccountDto;
import com.jira.pojo.dto.UserDto;
import com.jira.repos.AccountRepo;
import com.jira.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {
    @Autowired
    AccountRepo accountRepo;

    @Override
    public void setManager() {

    }

    @Override
    public List<AccountDto> findAll(){
        List<Account> accountList = accountRepo.findAll();
        List<AccountDto> accountDtos = new ArrayList<>();

        for (Account account: accountList) {
            accountDtos.add(new AccountDto(
                    account.getId(),
                    account.getName(),
                    account.getSurname(),
                    account.getEmail(),
                    new UserDto(
                            account.getUser().getId(),
                            account.getUser().getLogin(),
                            account.getUser().getPassword(),
                            account.getUser().getRoles())
            ));
        }

        return accountDtos;
    }
}
