package com.jira.services.impl;

import com.jira.models.Account;
import com.jira.models.ERole;
import com.jira.models.Role;
import com.jira.models.User;
import com.jira.pojo.dto.AccountDto;
import com.jira.pojo.dto.UserDto;
import com.jira.repos.AccountRepo;
import com.jira.repos.RoleRepo;
import com.jira.repos.UserRepo;
import com.jira.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Override
    public void setManager(Long id) {
        User user = userRepo.getById(id);
        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepo
                .findByName(ERole.ROLE_MANAGER)
                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
        roles.add(userRole);

        user.setRoles(roles);

        userRepo.save(user);
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
                    UserDto.build(account.getUser())
            ));
        }

        return accountDtos;
    }
}
