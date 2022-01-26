package com.jira.services;

import com.jira.pojo.dto.AccountDto;
import com.jira.pojo.dto.UserDto;
import com.jira.models.User;
import java.util.List;

public interface AdminService {
    void setManager(Long id);

    List<User> findAll();
}
