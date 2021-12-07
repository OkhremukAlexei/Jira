package com.jira.services;

import com.jira.models.Account;
import com.jira.models.User;

import java.util.List;

public interface UserService {
    List <User> getAll();
    User getOne(User us);
    User put(User us);
    void delete(User us);
    void deleteById (User us);
    List<User> getUsers();
}
