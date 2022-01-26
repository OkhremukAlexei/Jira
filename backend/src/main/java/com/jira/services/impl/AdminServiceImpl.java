package com.jira.services.impl;

import com.jira.models.ERole;
import com.jira.models.Role;
import com.jira.models.User;
import com.jira.repos.AccountRepo;
import com.jira.repos.RoleRepo;
import com.jira.repos.UserRepo;
import com.jira.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<User> findAll(){
        List<User> userList = userRepo.findAll();
        return userList;
    }
}
