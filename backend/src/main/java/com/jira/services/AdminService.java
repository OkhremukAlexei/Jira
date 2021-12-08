package com.jira.services;

import com.jira.pojo.dto.AccountDto;

import java.util.List;

public interface AdminService {
    void setManager();
    List<AccountDto> findAll();
}
