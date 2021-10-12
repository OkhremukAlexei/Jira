package com.jira.repos;

import com.jira.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account,Integer> {
}
