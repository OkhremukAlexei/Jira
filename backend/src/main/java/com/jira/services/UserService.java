package com.jira.services;

import com.jira.models.Account;
import com.jira.models.User;
import com.jira.pojo.dto.UserDto;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getOne(User us);
    User put(User us);
    void delete(User us);
    List<UserDto> getAllUsers();
    List<UserDto> getUsersOutsideTheProject(long id);
    List<User> getUsers();
}
