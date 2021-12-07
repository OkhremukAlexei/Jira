package com.jira.services.impl;

import com.jira.models.Account;
import com.jira.models.ERole;
import com.jira.models.User;
import com.jira.repos.UserRepo;
import com.jira.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UsersServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public User getOne(User us) {
        return userRepo.findById(us.getId()).get();
    }

    @Override
    public User put(User us) {
        return userRepo.save(us);
    }

    @Override
    public void delete(User us) {
        userRepo.delete(us);
    }

    @Override
    public void deleteById(User us) { userRepo.deleteById(us.getId());}

    @Override
    public List<User> getUsers() {
        return userRepo.findBySpecificRoles(ERole.ROLE_USER);
    }


}
