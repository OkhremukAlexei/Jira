package com.jira.services;

import com.jira.pojo.dto.AccountDto;
import com.jira.pojo.dto.UserDto;

import java.util.List;

public interface AdminService {
    void setManager(Long id);

    List<UserDto> findAll();
}
